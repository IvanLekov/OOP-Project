package bg.tu_varna.sit.f24621660.dnd.models.monster;

import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Armor;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;

public abstract class Monster {
    protected ResourceStat health;
    protected PowerStat strength;
    protected PowerStat mana;
    protected LevelStat level;
    protected Armor armor;


    protected void levelBonus(int level) {
        int levelBonus = level - 1;

        this.health.increaseMax(levelBonus * 10);
        this.health.increase(levelBonus * 10);
        this.strength.increase(levelBonus * 10);
        this.mana.increase(levelBonus * 10);

        this.armor = new Armor("Dragon Scales", 0.15 + (levelBonus * 0.05));
    }

    public boolean isAlive() {
        return this.health.getValue() > 0;
    }

}
