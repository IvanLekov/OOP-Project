package bg.tu_varna.sit.f24621660.dnd.io.loaders;

import bg.tu_varna.sit.f24621660.dnd.io.parsers.MapParser;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;

import java.util.List;

public class MapLoader {
    private final GameFileReader fileReader;
    private final MapParser mapParser;

    public MapLoader(GameFileReader fileReader, MapParser mapParser) {
        this.fileReader = fileReader;
        this.mapParser = mapParser;
    }

    public char[][] load(String filePath) {
        List<String> rawLines = fileReader.readLines(filePath);
        return mapParser.parse(rawLines);
    }
}