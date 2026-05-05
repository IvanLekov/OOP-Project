package bg.tu_varna.sit.f24621660.dnd.entities.stats.models;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Progressable;

public class LevelStat implements Progressable {
    private int value;

    public LevelStat(int startLevel) {
        this.value = startLevel;
    }

    @Override
    public void next() {
        this.value++;
    }
}