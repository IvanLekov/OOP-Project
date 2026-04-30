package bg.tu_varna.sit.f24621660.dnd.entities.stats.models;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;

public class PowerStat implements Attribute {
    private int value;

    public PowerStat(int startValue) {
        this.value = startValue;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void upgrade(int points) {
        this.value += points;
    }
}