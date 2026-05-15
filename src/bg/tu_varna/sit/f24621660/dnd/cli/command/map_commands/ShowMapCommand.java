package bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public class ShowMapCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {
        if (context.getGameMap() == null) {
            return "No active game.";
        }

        return "\n====== Current Map =======\n" +
                context.getGameMap().toString() + "\n" +
                "===========================";
    }
}