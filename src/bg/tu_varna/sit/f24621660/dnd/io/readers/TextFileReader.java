package bg.tu_varna.sit.f24621660.dnd.io.readers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileReader implements GameFileReader {

    @Override
    public List<String> readLines(String filePath) {
        try {
            return Files.lines(Path.of(filePath))
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new UncheckedIOException("Error reading file: " + filePath, e);
        }
    }
}