package bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.HeroFactory;
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
            return "Missing class input. Available classes: human, mage, warrior.";
        }

        String raceInput = args[0].trim().toLowerCase();

        Hero hero = switch (raceInput) {
            case "human" -> HeroFactory.createHuman();
            case "mage" -> HeroFactory.createMage();
            case "warrior" -> HeroFactory.createWarrior();
            default -> null;
        };

        if (hero == null) {
            return "Unknown class '" + args[0] + "'. Please choose human, mage, or warrior.";
        }

        try {
            LevelData levelData = levelBuilder.buildLevel(1);

            context.setHero(hero);
            context.loadLevel(levelData);

            context.getStateManager().changeTo(State.EXPLORATION);

            return "Started a new game with a " + raceInput + "!";

        } catch (Exception e) {
            return "Failed to generate level: " + e.getMessage();
        }
    }
}