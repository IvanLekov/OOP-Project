package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands.ShowMapCommand;
import bg.tu_varna.sit.f24621660.dnd.combat.models.Battle;
import bg.tu_varna.sit.f24621660.dnd.combat.logic.AttackManager;
import bg.tu_varna.sit.f24621660.dnd.combat.logic.TurnManager;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.StateManager;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.MonsterFactory;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.InteractionType;

import java.util.Random;

public class MoveCommand implements Command {

    private final Random random;
    private final ShowMapCommand showMapCommand;

    public MoveCommand(Random random, ShowMapCommand showMapCommand) {
        this.random = random;
        this.showMapCommand = showMapCommand;
    }

    @Override
    public String execute(GameContext context, String[] args) {
        if (context.getHero() == null || context.getCurrentLevel() == null) {
            return "No active game or level loaded.";
        }

        StateManager stateManager = context.getStateManager();

        if (stateManager.getCurrent() == State.COMBAT) {
            return "You are in a fight, you cannot run away.";
        }
        if (stateManager.getCurrent() == State.LOOTING) {
            return "You are looting, handle the treasure first.";
        }
        if (stateManager.getCurrent() == State.LEVEL_UP) {
            return "You have reached the exit, allocate your points and move to the next level.";
        }

        if (args.length == 0) {
            return "Please specify a direction (up, down, left, right).";
        }

        String direction = args[0].toLowerCase();
        int dx = 0;
        int dy = 0;

        switch (direction) {
            case "up" -> dx = -1;
            case "down" -> dx = 1;
            case "left" -> dy = -1;
            case "right" -> dy = 1;
            default -> {
                return "Invalid direction: '" + direction + "'. Use up, down, left, or right.";
            }
        }

        InteractionType interaction = context.getCurrentLevel().mapManager().moveHero(dx, dy);

        String resultMessage = switch (interaction) {
            case WALL -> "You tried walking into a wall. [#]";
            case PATH -> "You moved successfully.";
            case MONSTER -> handleMonsterEncounter(context);
            case TREASURE -> handleTreasureEncounter(context);
            case EXIT -> handleExitReached(context);
        };

        if (interaction == InteractionType.PATH || interaction == InteractionType.WALL) {
            String mapString = showMapCommand.execute(context, new String[0]);
            return resultMessage + "\n\n" + mapString;
        }

        return resultMessage;
    }

    private String handleMonsterEncounter(GameContext context) {
        int level = context.getHero().getLevel().getValue();

        Monster dragon = MonsterFactory.createDragon(level);

        AttackManager attackManager = new AttackManager(context.getHero(), dragon, random);
        TurnManager turnManager = new TurnManager(random);
        Battle battle = new Battle(context.getHero(), dragon, attackManager, turnManager);

        context.startBattle(battle);

        context.getStateManager().changeTo(State.COMBAT);

        return "You have encountered a monster!\nYou are now in combat.";
    }

    private String handleTreasureEncounter(GameContext context) {

        Item foundItem = context.getCurrentLevel().itemTable().drawRandomItem();
        context.setLoot(foundItem);

        context.getStateManager().changeTo(State.LOOTING);

        StringBuilder sb = new StringBuilder();
        sb.append("You found a treasure [").append(foundItem.getName()).append("]\n");
        String exactType = foundItem.getClass().getSimpleName();

        if (foundItem instanceof OffensiveItem offensive) {
            int percent = (int) Math.round(offensive.getDamageIncrease() * 100);
            sb.append("Type: ").append(exactType).append(" | Damage increase: ").append(percent).append("%");
        } else if (foundItem instanceof DefensiveItem defensive) {
            int percent = (int) Math.round(defensive.getDamageReduction() * 100);
            sb.append("Type: ").append(exactType).append(" | Defense reduction: ").append(percent).append("%");
        }

        return sb.toString();
    }

    private String handleExitReached(GameContext context) {
        context.getStateManager().changeTo(State.LEVEL_UP);

        context.startLevelUpSession();

        return "You have reached the end of the level!\n" +
                "You gained 30 stat points. Type 'allocate <stat> <points>' to upgrade your hero.";
    }
}