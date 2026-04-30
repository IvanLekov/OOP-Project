package bg.tu_varna.sit.f24621660.dnd.utills.loaders;

import bg.tu_varna.sit.f24621660.dnd.utills.parsers.MapParser;
import bg.tu_varna.sit.f24621660.dnd.utills.readers.TextFileReader;

import java.util.List;

public class MapLoader {
    private final TextFileReader fileReader;
    private final MapParser mapParser;

    public MapLoader() {
        this.fileReader = new TextFileReader();
        this.mapParser = new MapParser();
    }

    public char[][] load(int mapLevel) {
        String filePath = "resources/map_level_" + mapLevel + ".txt";
        List<String> rawLines = fileReader.readLines(filePath);
        return mapParser.parse(rawLines, mapLevel);
    }
}