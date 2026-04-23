package bg.tu_varna.sit.f24621660.dnd.entities.base;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;

public abstract class Combatant {

    private final Resource health;
    private final Attribute strength;
    private final Attribute mana;

    private DefensiveItem armor;

    public Combatant(Resource health, Attribute strength, Attribute mana, DefensiveItem armor) {
        this.health = health;
        this.strength = strength;
        this.mana = mana;
        this.armor = armor;
    }

    public void takeDamage(int damage) {
        int finalDamage = damage;
        if (this.armor != null) {
            finalDamage = this.armor.calculateReducedDamage(damage);
        }
        this.health.deplete(finalDamage);
    }
    public boolean isDead() {
        return this.health.getValue() <= 0;
    }

    public boolean isAlive() {
        return !isDead();
    }

    public abstract void onVictory();
    public abstract int getStrengthAttackDamage();
    public abstract int getSpellAttackDamage();

    public Resource getHealth() {
        return health;
    }

    public Attribute getStrength() {
        return strength;
    }

    public Attribute getMana() {
        return mana;
    }

    public DefensiveItem getArmor() {
        return armor;
    }

    public void setArmor(DefensiveItem armor) {
        this.armor = armor;
    }

}