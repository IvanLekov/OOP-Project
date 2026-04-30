package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.enums.GameStatus;
import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;

public abstract class Monster extends Combatant {

    protected Monster(Resource health, Attribute strength, Attribute mana) {
        super(health, strength, mana);
    }

    @Override
    public int getStrengthDamage() {
        return this.getStrength().getValue();
    }

    @Override
    public int getSpellDamage() {
        return this.getMana().getValue();
    }

    @Override
    public void handleVictory() {
        GameState.getInstance().setStatus(GameStatus.GAME_OVER);
    }
}