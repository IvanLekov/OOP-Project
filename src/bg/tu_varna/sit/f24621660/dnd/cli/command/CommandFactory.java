package bg.tu_varna.sit.f24621660.dnd.cli.command;

import java.util.Map;

public class CommandFactory {

    private final Map<String, Command> registeredCommands;

    public CommandFactory(Map<String, Command> commands) {
        if (commands == null) {
            throw new IllegalArgumentException("Commands map cannot be null.");
        }

        this.registeredCommands = Map.copyOf(commands);
    }

    public Command getCommand(String commandName) {
        return registeredCommands.get(commandName);
    }
}