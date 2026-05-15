package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class AllocateCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        if (GameState.current() != State.LEVEL_UP) {
            return "You can only allocate stats when you reach the end of a level.";
        }
        if (context.isLevelUpProcessed()) {
            return "Stats for this level are already allocated.";
        }
        if (args.length != 2) {
            return "Invalid format.";
        }

        String statName = args[0].toLowerCase();
        int points;

        try {
            points = Integer.parseInt(args[1]);
            if (points < 0) {
                return "You cannot allocate negative points.";
            }
        } catch (NumberFormatException e) {
            return "Invalid points! Please enter a valid number.";
        }

        switch (statName) {
            case "strength" -> context.addTempStats(points, 0, 0);
            case "mana" -> context.addTempStats(0, points, 0);
            case "health" -> context.addTempStats(0, 0, points);
            default -> {
                return "Unknown stat: '" + statName + ".";
            }
        }

        int totalAllocated = context.getTotalTempStats();

        return String.format("Allocated %d points to %s." +
                        "\nTotal points allocated so far: %d / 30." +
                        "\nType 'allocate_done' when finished.",
                points, statName, totalAllocated);
    }
}