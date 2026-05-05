package bg.tu_varna.sit.f24621660.dnd.cli.command;

import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public interface Command {
    String execute(GameContext context, String[] args);
}