package bg.tu_varna.sit.f24621660.dnd.entities.stats;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Stat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Upgradeable;

public class PowerStat implements Stat, Upgradeable {
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
        // При атрибутите надграждането просто увеличава стойността
        this.value += points;
    }
}