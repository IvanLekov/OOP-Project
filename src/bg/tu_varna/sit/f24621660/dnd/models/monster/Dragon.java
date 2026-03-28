package bg.tu_varna.sit.f24621660.dnd.models.monster;

import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;

public class Dragon extends Monster{

    public Dragon(int level) {
        this.health = new ResourceStat(50);
        this.mana = new PowerStat(25);
        this.strength = new PowerStat(25);
        this.level = new LevelStat(1);

        levelBonus(level);
    }

    public ResourceStat getHealth() {
        return health;
    }

    public PowerStat getStrength() {
        return strength;
    }

    public PowerStat getMana() {
        return mana;
    }

    public void takeDamage(double incomingDamage) {
        this.health.decrease((int) armor.calculateReducedDamage(incomingDamage));
    }
}