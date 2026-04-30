package bg.tu_varna.sit.f24621660.dnd.utills.loaders;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.utills.parsers.ItemParser;
import bg.tu_varna.sit.f24621660.dnd.utills.readers.TextFileReader;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader {
    private static final String FILE_PATH = "resources/items.txt";

    private final TextFileReader fileReader;
    private final ItemParser itemParser;

    public ItemLoader() {
        this.fileReader = new TextFileReader();
        this.itemParser = new ItemParser();
    }

    public List<Item> load(int mapLevel) {
        List<String> rawLines = fileReader.readLines(FILE_PATH);
        List<Item> result = new ArrayList<>();

        for (String line : rawLines) {
            Item item = itemParser.parseLine(line, mapLevel);
            if (item != null) {
                result.add(item);
            }
        }

        return result;
    }
}