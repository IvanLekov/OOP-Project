package bg.tu_varna.sit.f24621660.dnd.cli.command.level;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
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

        if (GameState.current() != State.LEVEL_UP) {
            return "You need to first reach the exit.";
        }
        if (!context.isLevelUpProcessed()) {
            return "Use 'allocate' to upgrade your hero first.";
        }
        int nextLevelNumber = context.getHero().getLevel().getValue();

        LevelData levelData = levelBuilder.buildLevel(nextLevelNumber);

        context.setGameMap(levelData.map());
        context.setMapManager(levelData.mapManager());
        context.setItemTable(levelData.itemTable());

        GameState.changeTo(State.EXPLORATION);

        return "You are now on level " + nextLevelNumber + ".";
    }
}