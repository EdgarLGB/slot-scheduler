package com.criteo;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Slot {
    private int slotId;
    private int groupId;

    @PlanningVariable(valueRangeProviderRefs = "itemRange")
    private Item item;

    public Slot() {}

    public Slot(int slotId, int groupId) {
        this.slotId = slotId;
        this.groupId = groupId;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotId=" + slotId +
                ", groupId=" + groupId +
                ", item=" + item +
                '}';
    }
}
