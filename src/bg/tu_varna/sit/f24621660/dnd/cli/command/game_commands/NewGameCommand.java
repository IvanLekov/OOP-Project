package bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.race.Human;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.race.Mage;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.race.Warrior;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;
import bg.tu_varna.sit.f24621660.dnd.world.models.level.LevelData;


public class NewGameCommand implements Command {

    private final LevelBuilder levelBuilder;

    public NewGameCommand(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    @Override
    public String execute(GameContext context, String[] args) {

        if (args == null || args.length == 0) {
            return "Missing race input";
        }

        String raceInput = args[0].trim().toLowerCase();

        Hero hero = switch (raceInput) {
            case "human" -> new Human();
            case "mage" -> new Mage();
            case "warrior" -> new Warrior();
            default -> null;
        };

        if (hero == null) {
            return "Unknown race '" + args[0] + ".";
        }

        LevelData levelData = levelBuilder.buildLevel(1);

        context.setHero(hero);
        context.setGameMap(levelData.map());
        context.setMapManager(levelData.mapManager());
        context.setItemTable(levelData.itemTable());

        GameState.changeTo(State.EXPLORATION);

        return "Started a new game with " + raceInput;
    }
}