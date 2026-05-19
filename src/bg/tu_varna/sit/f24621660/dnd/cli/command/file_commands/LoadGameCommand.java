package bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.io.game.GameStorage;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;
import bg.tu_varna.sit.f24621660.dnd.world.models.level.LevelData;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;

public class LoadGameCommand implements Command {

    private final GameStorage storage;
    private final LevelBuilder levelBuilder;

    public LoadGameCommand(GameStorage storage, LevelBuilder levelBuilder) {
        this.storage = storage;
        this.levelBuilder = levelBuilder;
    }

    @Override
    public String execute(GameContext context, String[] args) {
        if (args == null || args.length == 0) {
            return "Usage: load_game <file_name>";
        }

        String fileName = args[0];

        try {
            storage.load(context, fileName);

            int levelIndex = context.getSavedLevelIndex();
            Position heroPos = context.getSavedHeroPosition();
            java.util.List<String> savedRows = context.getSavedMapRows();

            LevelData levelData = levelBuilder.buildLevel(levelIndex);
            context.loadLevel(levelData);

            context.getCurrentLevel().mapManager().restoreMapLayout(savedRows);

            context.getCurrentLevel().mapManager().teleportHero(heroPos);

            context.clearSavedMapData();

            return "Game successfully loaded from '" + fileName + "'!\n" +
                    "Hero Level: " + context.getHero().getLevel().getValue() + "\n" +
                    "Current State: " + context.getStateManager().getCurrent();

        } catch (Exception e) {
            return "Error while loading game: " + e.getMessage();
        }
    }
}