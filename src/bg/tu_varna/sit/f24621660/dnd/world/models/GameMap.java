package bg.tu_varna.sit.f24621660.dnd.world.models;


import bg.tu_varna.sit.f24621660.dnd.io.loaders.MapLoader;

public class GameMap {
    private final char[][] grid;

    public GameMap(int mapLevel, MapLoader mapLoader) {
        this.grid = mapLoader.load(mapLevel);
    }

    public void setCell(int x, int y, char symbol) {
        if (isValidBounds(x, y)) {
            grid[x][y] = symbol;
        }
    }

    public char getCell(int x, int y) {
        if (isValidBounds(x, y)) {
            return grid[x][y];
        }
        return '#';
    }

    public boolean isWalkable(int x, int y) {
        return isValidBounds(x, y) && grid[x][y] != '#';
    }


    private boolean isValidBounds(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public int getRowsCount() { return grid.length; }
    public int getColsCount() { return grid[0].length; }
}