package bg.tu_varna.sit.f24621660.dnd.cli.command.level_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;
import bg.tu_varna.sit.f24621660.dnd.world.models.level.LevelData;

public class NextLevelCommand implements Command {

    private final LevelBuilder levelBuilder;

    public NextLevelCommand(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.LEVEL_UP) {
            return "You need to reach the exit of the current level first.";
        }

        if (context.getLevelUpSession() != null) {
            return "Use 'allocate' to upgrade your hero first, then type 'allocate_done'.";
        }

        int nextLevelNumber = context.getHero().getLevel().getValue();

        try {
            LevelData levelData = levelBuilder.buildLevel(nextLevelNumber);

            context.loadLevel(levelData);

            context.getStateManager().changeTo(State.EXPLORATION);

            return "You descend deeper into the dungeon...\n" +
                    "Welcome to Level " + nextLevelNumber + "!";

        } catch (Exception e) {
            // ДОБАВЯМЕ ТОВА: Нека конзолата ни каже каква е реалната грешка!
            e.printStackTrace();

            context.getStateManager().changeTo(State.GAME_OVER);
            return "There are no more levels to explore. Congratulations, you have beaten the game!\n(Debug error: " + e.getMessage() + ")";
        }
    }
}