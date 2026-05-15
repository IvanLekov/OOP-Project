package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

public class LootDiscardCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        if (GameState.current() != State.LOOTING) {
            return "You are not looting.";
        }

        Item loot = context.getCurrentLoot();
        context.clearCurrentLoot();
        GameState.changeTo(State.EXPLORATION);

        return "You decided to leave the [" + loot.getName() + "] behind.\n" +
                "You are now exploring.";
    }
}
