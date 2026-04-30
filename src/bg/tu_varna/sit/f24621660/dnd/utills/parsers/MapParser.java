package bg.tu_varna.sit.f24621660.dnd.utills.parsers;

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
            String line = lines.get(i);//reads a row one by one
            if (line.length() != cols) {
                throw new IllegalArgumentException("Inconsistent row length in map level " + mapLevel);
            }
            grid[i] = line.toCharArray();//converts the red row to char array
        }

        return grid;//returns the red row
    }
}