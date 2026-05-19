package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelUpSession;

public class AllocateDoneCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.LEVEL_UP) {
            return "You are not currently leveling up.";
        }

        LevelUpSession session = context.getLevelUpSession();
        if (session == null) {
            return "Error: No active level up session. Stats might be already allocated.";
        }

        if (session.getRemainingPoints() > 0) {
             return "You still have " + session.getRemainingPoints() + " points to allocate. Use 'allocate' command first.";
        }

        int str = session.getPendingStrength();
        int mana = session.getPendingMana();
        int hp = session.getPendingHealth();

        try {
            context.getHero().levelUp(str, mana, hp);

            context.clearLevelUpSession();

            return "Level up complete! You successfully allocated your points.\n" +
                    "Type 'next_level' to proceed to your next adventure.";

        } catch (IllegalArgumentException e) {
            context.clearLevelUpSession();
            return "Error during allocation: " + e.getMessage() + "\nAll allocated points have been reset. Type 'allocate' to start over.";
        }
    }
}