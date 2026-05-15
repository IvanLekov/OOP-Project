package bg.tu_varna.sit.f24621660.dnd.cli.command.level;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;

import java.util.List;

public class LoadLevelCommand implements Command {

    private final GameFileReader fileReader;

    public LoadLevelCommand(GameFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public String execute(GameContext context, String[] args) {
        if (args.length != 1) {
            return "Invalid format! Usage: load_level <number>";
        }

        String targetLevel = args[0];
        String filePath = "resources/map_level_" + targetLevel + ".txt";

        try {
            List<String> rawMapLines = fileReader.readLines(filePath);

            StringBuilder sb = new StringBuilder();
            sb.append("\n=== Map Outline: Level ").append(targetLevel).append(" ===\n");

            for (String line : rawMapLines) {
                sb.append(line).append("\n");
            }

            return sb.toString().trim();

        } catch (Exception e) {
            return "Error: Could not load outline for level " + targetLevel + ". (File not found: " + filePath + ")";
        }
    }
}