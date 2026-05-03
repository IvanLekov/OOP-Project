package bg.tu_varna.sit.f24621660.dnd.io.parsers;

import java.util.List;

public class MapParser {

    public char[][] parse(List<String> lines, int mapLevel) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalStateException("Map data is invalid for level " + mapLevel);
        }

        int rows = lines.size();
        int cols = lines.getFirst().length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            if (line.length() != cols) {
                throw new IllegalArgumentException("Inconsistent row length in map level " + mapLevel);
            }
            grid[i] = line.toCharArray();
        }

        return grid;
    }
}