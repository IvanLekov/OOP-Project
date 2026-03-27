package bg.tu_varna.sit.f24621660.dnd.models.monster;

import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;

public class Dragon {
    private ResourceStat health;
    private PowerStat strength;
    private PowerStat mana;
    private double armorPercentage;

    public Dragon(int level) {
        int baseHealth = 50;
        int baseStrength = 25;
        int baseMana = 25;
        double baseArmor = 0.15;

        int levelBonus = level - 1;

        this.health = new ResourceStat(baseHealth + (levelBonus * 10));
        this.strength = new PowerStat(baseStrength + (levelBonus * 10));
        this.mana = new PowerStat(baseMana + (levelBonus * 10));

        this.armorPercentage = baseArmor + (levelBonus * 0.05);
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

    public double getArmorPercentage() {
        return armorPercentage;
    }

    public boolean isAlive() {
        return this.health.getValue() > 0;
    }

    public void takeDamage(int incomingDamage) {
        double blockedDamage = incomingDamage * this.armorPercentage;
        int actualDamage = (int) (incomingDamage - blockedDamage);
        this.health.decrease(actualDamage);
    }
}