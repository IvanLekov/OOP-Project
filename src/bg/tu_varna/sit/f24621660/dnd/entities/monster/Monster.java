package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;

public abstract class Monster extends Combatant {

    protected Monster(Resource health, Attribute strength, Attribute mana, DefensiveItem dragonArmor) {
        super(health, strength, mana, dragonArmor);
    }

    @Override
    public int getStrengthAttackDamage() {
        return this.getStrength().getValue();
    }

    @Override
    public int getSpellAttackDamage() {
        return this.getMana().getValue();
    }

    @Override
    public void onVictory() {
        //gameOver;
    }
}