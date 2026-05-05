package bg.tu_varna.sit.f24621660.dnd.io.readers;

import java.util.List;

public interface GameFileReader {
    List<String> readLines(String sourcePath);
}