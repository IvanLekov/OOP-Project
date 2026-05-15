package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands.ShowMapCommand;
import bg.tu_varna.sit.f24621660.dnd.combat.Battle;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Dragon; // Увери се, че импортът съвпада с твоята структура
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.InteractionType;

public abstract class BaseMoveCommand implements Command {
    private final int dx;
    private final int dy;

    protected BaseMoveCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public String execute(GameContext context, String[] args) {
        if (context.getHero() == null) {
            return "No active game.";
        }

        if (GameState.current() == State.COMBAT) {
            return "You are in a fight, you cannot run away.";
        }
        if (GameState.current() == State.LOOTING) {
            return "You are looting, handle the treasure first.";
        }
        if (GameState.current() == State.LEVEL_UP) {
            return "You have reached the exit, allocate your points and move to the next level.";
        }

        InteractionType interaction = context.getMapManager().moveHero(dx, dy);

        String resultMessage = switch (interaction) {
            case WALL -> "You tried walking into a wall. [#]";
            case PATH -> "You moved successfully.";
            case MONSTER -> handleMonsterEncounter(context);
            case TREASURE -> handleTreasureEncounter(context);
            case EXIT -> handleExitReached(context);
        };

        // --- НОВАТА ЛОГИКА ЗА АВТОМАТИЧНА КАРТА ---
        // Показваме картата само ако играчът просто се разхожда или се е блъснал.
        // Ако е срещнал чудовище или съкровище, оставяме фокуса върху битката/плячката.
        if (interaction == InteractionType.PATH || interaction == InteractionType.WALL) {

            ShowMapCommand showMap = new ShowMapCommand();

            String mapString = showMap.execute(context, new String[0]);

            return resultMessage + "\n\n" + mapString;
        }

        return resultMessage;
    }

    private String handleMonsterEncounter(GameContext context) {
        int level = context.getHero().getLevel().getValue();
        Monster dragon = new Dragon(level);
        Battle battle = new Battle(context.getHero(), dragon);
        context.setCurrentBattle(battle);

        return "You have encountered a monster!\n" +
                "You are now in a combat.";
    }

    private String handleTreasureEncounter(GameContext context) {

        Item foundItem = context.getItemTable().drawRandomItem();
        context.setCurrentLoot(foundItem);
        GameState.changeTo(State.LOOTING);

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
        GameState.changeTo(State.LEVEL_UP);

        return "You have reached the end.";
    }
}