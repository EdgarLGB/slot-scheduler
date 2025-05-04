package com.criteo;

import org.optaplanner.core.api.domain.entity.PlanningEntity;

import java.util.Objects;

@PlanningEntity
public class Item {
    private int feedId;
    private int itemId;

    public Item() {}

    public Item(int feedId, int itemId) {
        this.feedId = feedId;
        this.itemId = itemId;
    }

    public int getFeedId() {
        return feedId;
    }

    public int getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return feedId == item.feedId && itemId == item.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedId, itemId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "feedId=" + feedId +
                ", itemId=" + itemId +
                '}';
    }
}
