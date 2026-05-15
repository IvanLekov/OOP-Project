package bg.tu_varna.sit.f24621660.dnd.cli.command;

import java.util.Map;

public class CommandFactory {

    private final Map<String, Command> registeredCommands;

    public CommandFactory(Map<String, Command> commands) {
        this.registeredCommands = commands;
    }

    public Command getCommand(String commandName) {
        return registeredCommands.get(commandName);
    }
}