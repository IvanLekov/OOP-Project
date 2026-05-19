package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.combat.models.AttackType;
import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;

public class Monster extends Combatant {

    private final DefensiveItem armor;

    public Monster(Resource health, Attribute strength, Attribute mana, DefensiveItem armor) {
        super(health, strength, mana);
        this.armor = armor;
    }

    @Override
    public int calculateDamage(AttackType attackType) {
        switch (attackType) {
            case POWER:
                return this.getStrength().getValue();
            case SPELL:
                return this.getMana().getValue();
            default:
                throw new IllegalArgumentException("Unknown attack type: " + attackType);
        }
    }

    @Override
    public void takeDamage(int amount) {
        // Ако има броня, намалява демиджа. Ако няма (armor == null), поема целия.
        int finalDamage = (this.armor != null) ? armor.calculateReducedDamage(amount) : amount;
        this.getHealth().deplete(finalDamage);
    }
}