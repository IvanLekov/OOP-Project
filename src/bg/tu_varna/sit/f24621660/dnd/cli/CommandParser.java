package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.CommandFactory;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

import java.util.Arrays;

public class CommandParser {
    private final CommandFactory commandFactory;

    public CommandParser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public String processInput(GameContext context, String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please enter a command.";
        }

        String[] tokens = input.trim().toLowerCase().split("\\s+");
        String commandName = tokens[0];
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        Command command = commandFactory.getCommand(commandName);

        if (command == null) {
            return "Unknown command: '" + commandName + "'.";
        }

        return command.execute(context, args);
    }
}