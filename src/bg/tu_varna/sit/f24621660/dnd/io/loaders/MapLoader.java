package bg.tu_varna.sit.f24621660.dnd.io.loaders;

import bg.tu_varna.sit.f24621660.dnd.io.parsers.MapParser;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;

import java.util.List;

public class MapLoader {
    private final GameFileReader fileReader;
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