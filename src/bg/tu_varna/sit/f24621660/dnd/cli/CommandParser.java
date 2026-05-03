package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.file.OpenCommand;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class CommandParser {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandParser() {
        // Тук регистрираме всички команди
        commands.put("open", new OpenCommand());
        // commands.put("close", new CloseCommand());
        // commands.put("save", new SaveCommand());
    }

    public String processInput(GameContext context, String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Моля, въведете команда.";
        }

        // Разделяме входа по интервали (един или повече)
        String[] tokens = input.trim().split("\\s+");
        String commandName = tokens[0].toLowerCase();

        // Взимаме само аргументите (всичко след първата дума)
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

        Command command = commands.get(commandName);
        if (command == null) {
            return "Грешка: Непозната команда '" + commandName + "'.";
        }

        // Изпълняваме командата и връщаме резултата
        return command.execute(context, args);
    }
}