package com.criteo;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class Scheduler {
    @PlanningEntityCollectionProperty
    private List<Slot> slots;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "itemRange")
    private List<Item> items;

    @PlanningScore
    private HardSoftScore score;

    public Scheduler() {}

    public Scheduler(List<Slot> slots, List<Item> items) {
        this.slots = slots;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "slots=" + slots +
                ", items=" + items +
                ", score=" + score +
                '}';
    }
}
