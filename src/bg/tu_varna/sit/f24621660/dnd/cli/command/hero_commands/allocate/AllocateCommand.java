package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelUpSession;

public class AllocateCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.LEVEL_UP) {
            return "You can only allocate stats when you reach the end of a level.";
        }

        LevelUpSession session = context.getLevelUpSession();
        if (session == null) {
            return "Error: No active level up session found.";
        }

        if (args.length != 2) {
            return "Usage: allocate <strength|mana|health> <points>";
        }

        String statName = args[0].toLowerCase();
        int points;

        try {
            points = Integer.parseInt(args[1]);
            if (points <= 0) {
                return "Points must be a positive number.";
            }
        } catch (NumberFormatException e) {
            return "Invalid points! Please enter a valid number.";
        }

        if (session.getTotalAllocated() + points > LevelUpSession.MAX_POINTS) {
            return "You cannot exceed the maximum of 30 points! Remaining points: " + session.getRemainingPoints();
        }

        switch (statName) {
            case "strength" -> session.addStrength(points);
            case "mana" -> session.addMana(points);
            case "health" -> session.addHealth(points);
            default -> {
                return "Unknown stat: '" + statName + "'. Use strength, mana, or health.";
            }
        }

        return String.format("Allocated %d points to %s." +
                        "\nTotal points allocated so far: %d / 30." +
                        "\nType 'allocate_done' when finished.",
                points, statName, session.getTotalAllocated());
    }
}