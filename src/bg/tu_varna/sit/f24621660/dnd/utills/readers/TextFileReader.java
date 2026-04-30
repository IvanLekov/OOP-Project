package bg.tu_varna.sit.f24621660.dnd.utills.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

    public List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {//reads each line
                if (line.trim().isEmpty() || line.startsWith("#")) {//ignores empty spaces and comments
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file: " + filePath, e);
        }

        return lines;//returns lines in String format
    }
}