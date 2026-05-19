package bg.tu_varna.sit.f24621660.dnd.io.loaders;

import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemConfig;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemParser;

import java.util.List;
import java.util.stream.Collectors;

public class ItemTableLoader {

    private final GameFileReader fileReader;
    private final ItemParser itemParser;

    public ItemTableLoader(GameFileReader fileReader, ItemParser itemParser) {
        this.fileReader = fileReader;
        this.itemParser = itemParser;
    }

    public List<Item> load(String filePath, int mapLevel) {
        List<String> rawLines = fileReader.readLines(filePath);

        return rawLines.stream()
                .map(itemParser::parseLine)
                .filter(config -> mapLevel >= config.minLevel() && mapLevel <= config.maxLevel())
                .map(ItemConfig::item)
                .collect(Collectors.toList());
    }
}