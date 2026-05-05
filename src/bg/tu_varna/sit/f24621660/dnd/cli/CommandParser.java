package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.CommandFactory;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class CommandParser {
    private final CommandFactory commandFactory;

    public CommandParser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public String processInput(GameContext context, String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Моля, въведете команда.";
        }

        String lowerInput = input.trim().toLowerCase();
        String commandName = extractCommandName(lowerInput);

        String[] args = extractArguments(lowerInput, commandName);

        State currentState = GameState.current();
        Command command = commandFactory.getCommand(currentState, commandName);

        if (command == null) {
            return "Непозната команда или не може да се използва в текущия режим (" + currentState + ").";
        }

        return command.execute(context, args);
    }

    private String extractCommandName(String input) {
        if (input.startsWith("move ") || input.startsWith("attack ") || input.startsWith("loot ") || input.startsWith("save as")) {
            String[] parts = input.split("\\s+");
            if (parts.length >= 2) {
                return parts[0] + " " + parts[1];
            }
        }
        return input.split("\\s+")[0];
    }

    private String[] extractArguments(String input, String commandName) {
        String argsString = input.substring(commandName.length()).trim();

        if (argsString.isEmpty()) {
            return new String[0];
        }

        return argsString.split("\\s+");
    }
}