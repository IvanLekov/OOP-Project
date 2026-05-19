package bg.tu_varna.sit.f24621660.dnd.entities.stats.models;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;

public class ResourceStat implements Resource {
    private int maxValue;
    private int currentValue;

    public ResourceStat(int maxValue) {
        if (maxValue <= 0) throw new IllegalArgumentException("Max value must be positive.");
        this.maxValue = maxValue;
        this.currentValue = maxValue;
    }

    @Override
    public int getValue() { return currentValue; }

    @Override
    public int getMaxValue() { return maxValue; }

    @Override
    public void upgrade(int points) {
        if (points < 0) throw new IllegalArgumentException("Upgrade points cannot be negative.");
        this.maxValue += points;
        this.currentValue += points;
    }

    @Override
    public void deplete(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Deplete amount cannot be negative.");
        this.currentValue = Math.max(0, this.currentValue - amount);
    }

    @Override
    public void restore(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Restore amount cannot be negative.");
        this.currentValue = Math.min(this.maxValue, this.currentValue + amount);
    }
}