package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands.CombatStatusCommand;
import bg.tu_varna.sit.f24621660.dnd.combat.models.Battle;
import bg.tu_varna.sit.f24621660.dnd.combat.models.AttackType;
import bg.tu_varna.sit.f24621660.dnd.combat.models.BattleStatus;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class AttackCommand implements Command {

    private final CombatStatusCommand combatStatusCommand;

    public AttackCommand(CombatStatusCommand combatStatusCommand) {
        this.combatStatusCommand = combatStatusCommand;
    }

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.COMBAT) {
            return "You are not in combat.";
        }

        Battle battle = context.getCurrentBattle();
        if (battle == null) {
            return "Error: No active battle found.";
        }

        if (args.length == 0) {
            return "Please specify an attack type (power, spell).";
        }

        String typeStr = args[0].toLowerCase();
        AttackType attackType;

        switch (typeStr) {
            case "power" -> attackType = AttackType.POWER;
            case "spell" -> attackType = AttackType.SPELL;
            default -> {
                return "Invalid attack type: '" + typeStr + "'. Use 'power' or 'spell'.";
            }
        }

        BattleStatus status = battle.processRound(attackType);

        if (status == BattleStatus.HERO_WON) {
            int maxHp = context.getHero().getHealth().getMaxValue();
            int healAmount = maxHp / 2;
            context.getHero().heal(healAmount);

            context.getStateManager().changeTo(State.EXPLORATION);
            context.clearBattle();

            return String.format("You defeated the monster and healed for %d HP (50%%)!\n" +
                    "You are now in exploration mode.", healAmount);

        } else if (status == BattleStatus.MONSTER_WON) {
            context.getStateManager().changeTo(State.GAME_OVER);
            return "You have been defeated by the monster";
        }

        return combatStatusCommand.execute(context, new String[0]);
    }
}