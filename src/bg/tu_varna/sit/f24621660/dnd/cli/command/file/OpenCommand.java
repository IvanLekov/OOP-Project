package bg.tu_varna.sit.f24621660.dnd.cli.command.file;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

// OpenCommand.java
public class OpenCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        if (args.length < 1) {
            return "Грешка: Не е зададен файл за отваряне. Употреба: open <filename>";
        }

        String fileName = args[0];

        // Тук по-късно ще сложиш логиката за четене от файла.

        context.setFileOpen(true);
        context.setCurrentFileName(fileName);

        return "Успешно: Файлът '" + fileName + "' беше зареден в паметта.";
    }
}