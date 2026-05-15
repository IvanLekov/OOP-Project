package bg.tu_varna.sit.f24621660.dnd.io.loaders;

import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemConfig;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemParser;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;

import java.util.ArrayList;
import java.util.List;

public class ItemTableLoader {

    private final GameFileReader fileReader;
    private final ItemParser itemParser;

    // Зависимостите се подават отвън (Dependency Injection)
    public ItemTableLoader(GameFileReader fileReader, ItemParser itemParser) {
        this.fileReader = fileReader;
        this.itemParser = itemParser;
    }

    public List<Item> load(String filePath, int mapLevel) {
        List<String> rawLines = fileReader.readLines(filePath);
        List<Item> result = new ArrayList<>();

        for (String line : rawLines) {
            ItemConfig config = itemParser.parseLine(line);

            if (mapLevel >= config.getMinLevel() && mapLevel <= config.getMaxLevel()) {
                result.add(config.getItem());
            }
        }

        return result;
    }
}