package bg.tu_varna.sit.f24621660.dnd.entities.stats;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Depletable;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Stat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.Upgradeable;

public class ResourceStat implements Stat, Upgradeable, Depletable {
    private int maxValue;
    private int currentValue;

    public ResourceStat(int maxValue) {
        this.maxValue = maxValue;
        this.currentValue = maxValue; // Започва на макс
    }

    @Override
    public int getValue() {
        return currentValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void upgrade(int points) {
        // При ресурсите, надграждането вдига МАКСИМУМА
        this.maxValue += points;
        // Опционално: лекува героя с новите точки
        this.currentValue += points;
    }

    @Override
    public void deplete(int amount) {
        this.currentValue -= amount;
        if (this.currentValue < 0) {
            this.currentValue = 0;
        }
    }

    @Override
    public void restore(int amount) {
        this.currentValue += amount;
        if (this.currentValue > this.maxValue) {
            this.currentValue = this.maxValue;
        }
    }
}
