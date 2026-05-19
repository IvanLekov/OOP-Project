package bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.combat.models.Battle;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class CombatStatusCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.COMBAT) {
            return "You are not in combat.";
        }

        Battle battle = context.getCurrentBattle();
        if (battle == null) {
            return "Error: No active battle found.";
        }

        int heroHp = battle.getHero().getHealth().getValue();
        int maxHeroHp = battle.getHero().getHealth().getMaxValue();

        int monsterHp = battle.getMonster().getHealth().getValue();
        int maxMonsterHp = battle.getMonster().getHealth().getMaxValue();

        String currentTurn = battle.getTurnManager().isHeroTurn() ? "Hero" : "Monster";

        return String.format(
                "\n--- Battle Status ---\n" +
                        "Turn: >>> %s <<<\n" +
                        "Hero HP: %d / %d\n" +
                        "Monster HP: %d / %d\n" +
                        "---------------------",
                currentTurn,
                heroHp, maxHeroHp,
                monsterHp, maxMonsterHp
        );
    }
}