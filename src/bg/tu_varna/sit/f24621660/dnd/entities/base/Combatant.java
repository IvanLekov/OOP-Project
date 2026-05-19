package bg.tu_varna.sit.f24621660.dnd.entities.base;

import bg.tu_varna.sit.f24621660.dnd.entities.base.contracts.Attacker;
import bg.tu_varna.sit.f24621660.dnd.entities.base.contracts.Damageable;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;

public abstract class Combatant implements Damageable, Attacker {

    private final Resource health;
    private final Attribute strength;
    private final Attribute mana;

    public Combatant(Resource health, Attribute strength, Attribute mana) {
        this.health = health;
        this.strength = strength;
        this.mana = mana;
    }

    @Override
    public boolean isAlive() {
        return health.getValue() > 0;
    }

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