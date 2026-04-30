package bg.tu_varna.sit.f24621660.dnd.entities.base;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;

public abstract class Combatant {

    private final Resource health;
    private final Attribute strength;
    private final Attribute mana;


    public Combatant(Resource health, Attribute strength, Attribute mana) {
        this.health = health;
        this.strength = strength;
        this.mana = mana;
    }

    public abstract int getStrengthDamage();
    public abstract int getSpellDamage();
    public abstract void takeDamage(int amount);
    public abstract void handleVictory();

    public Resource getHealth() {
        return health;
    }

    public Attribute getStrength() {
        return strength;
    }

    public Attribute getMana() {
        return mana;
    }

}