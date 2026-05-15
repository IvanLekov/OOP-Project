package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;

public class AllocateDoneCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        if (GameState.current() != State.LEVEL_UP) {
            return "You have already finalized your allocation.";
        }
        if (context.isLevelUpProcessed()) {
            return "Stats for this level are already allocated.";
        }

        int str = context.getTempStr();
        int mana = context.getTempMana();
        int hp = context.getTempHealth();

        try {
            context.getHero().levelUp(str, mana, hp);

            context.clearTempStats();
            context.setLevelUpProcessed(true);
            return "Уou allocated successfully";

        } catch (IllegalArgumentException e) {
            context.clearTempStats();
            return "Error: " + e.getMessage() + "\nAll allocated points have been reset.";
        }
    }
}