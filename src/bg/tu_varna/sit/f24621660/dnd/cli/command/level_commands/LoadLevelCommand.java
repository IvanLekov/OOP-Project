package bg.tu_varna.sit.f24621660.dnd.cli.command.level_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;

import java.util.List;

public class LoadLevelCommand implements Command {

    private final GameFileReader fileReader;

    public LoadLevelCommand(GameFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public String execute(GameContext context, String[] args) {

        if (args == null || args.length != 1) {
            return "Invalid format! Usage: load_level <number>";
        }

        String targetLevel = args[0].trim();

        String filePath = "resources/map_level_" + targetLevel + ".txt";

        try {
            List<String> rawMapLines = fileReader.readLines(filePath);

            if (rawMapLines == null || rawMapLines.isEmpty()) {
                return "Level " + targetLevel + " file is empty or corrupted.";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("\n=== Map Outline: Level ").append(targetLevel).append(" ===\n");

            for (String line : rawMapLines) {
                sb.append(line).append("\n");
            }

            sb.append("=============================");

            return sb.toString();

        } catch (Exception e) {
            return "Error: Could not load outline for level " + targetLevel;
        }
    }
}