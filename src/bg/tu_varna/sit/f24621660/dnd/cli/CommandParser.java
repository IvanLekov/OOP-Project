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

        String commandName = extractCommandName(tokens);
        String[] args = extractArguments(tokens, commandName);

        Command command = commandFactory.getCommand(commandName);

        if (command == null) {
            return "Unknown command: '" + commandName + "'.";
        }

        return command.execute(context, args);
    }

    private String[] extractArguments(String[] tokens, String commandName) {
        int commandWordsCount = commandName.split(" ").length;

        if (tokens.length <= commandWordsCount) {
            return new String[0];
        }

        return Arrays.copyOfRange(tokens, commandWordsCount, tokens.length);
    }

    private String extractCommandName(String[] tokens) {
        if (tokens.length >= 2 && isTwoWordCommand(tokens[0])) {
            return tokens[0] + " " + tokens[1];
        }

        return tokens[0];
    }

    private boolean isTwoWordCommand(String firstWord) {
        return firstWord.equals("move") ||
                firstWord.equals("attack") ||
                firstWord.equals("loot");
    }
}