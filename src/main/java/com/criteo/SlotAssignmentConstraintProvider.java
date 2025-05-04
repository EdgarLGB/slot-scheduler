package com.criteo;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class SlotAssignmentConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                slotShouldBeAssignedWithItemOfCorrespondingFeedId(constraintFactory),
                noSameItemAppearingInAdjacentGroups(constraintFactory),
                discourageItemReusePerSlotIdAcrossAllGroups(constraintFactory),
        };
    }

    // Hard constraint
    private Constraint slotShouldBeAssignedWithItemOfCorrespondingFeedId(ConstraintFactory factory) {
        return factory.forEach(Slot.class)
                .filter(slot -> slot.getItem().getFeedId() != slot.getSlotId())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Slot should be assigned with item of corresponding feedId");
    }

    // Hard constraint
    private Constraint noSameItemAppearingInAdjacentGroups(ConstraintFactory factory) {
        return factory.forEach(Slot.class)
                .join(Slot.class,
                        Joiners.equal(Slot::getSlotId),
                        Joiners.lessThan(Slot::getGroupId)) // avoid self-pair
                .filter((a, b) -> Math.abs(a.getGroupId() - b.getGroupId()) == 1
                        && a.getItem().equals(b.getItem()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Same item appearing in adjacent groups");
    }

    // Soft constraint
    private Constraint discourageItemReusePerSlotIdAcrossAllGroups(ConstraintFactory factory) {
        return factory.forEach(Slot.class)
                .join(Slot.class,
                        Joiners.equal(Slot::getSlotId),       // same slotId (e.g., always slot 0)
                        Joiners.equal(Slot::getItem),         // same item
                        Joiners.lessThan(Slot::getGroupId))   // different group, avoid duplicates
                .penalize(HardSoftScore.ofSoft(10))  // soft score to discourage the same item reuse
                .asConstraint("Discourage same item reuse in same slotId across groups");
    }
}
