package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.combat.AttackType;
import bg.tu_varna.sit.f24621660.dnd.combat.Battle;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public abstract class BaseAttackCommand implements Command {
    private final AttackType attackType;

    protected BaseAttackCommand(AttackType attackType) {
        this.attackType = attackType;
    }

    @Override
    public String execute(GameContext context, String[] args) {

        if (GameState.current() != State.COMBAT) {
            return "You are not in combat.";
        }

        Battle battle = context.getCurrentBattle();
        if (battle == null) {
            return "Error: No active battle found.";
        }

        battle.processTurn(attackType);

        if (GameState.current() == State.EXPLORATION) {
            context.clearCurrentBattle();
            return "You defeated the monster.\n" +
                    "You are now in exploration mode.";

        } else if (GameState.current() == State.GAME_OVER) {
            return "You have been defeated by the monster.";
        }

        return "\n--- Battle Status ---\n" +
                battle.healthState() + "\n" +
                "---------------------\n" +
                battle.turnState();
    }
}