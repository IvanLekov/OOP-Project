package bg.tu_varna.sit.f24621660.dnd.world;


import bg.tu_varna.sit.f24621660.dnd.utills.loaders.MapLoader;

public class GameMap {
    private final char[][] grid;
    private final Position startPosition;
    private final Position exitPosition;

    public GameMap(int mapLevel, MapLoader mapLoader) {
        this.grid = mapLoader.load(mapLevel);

        this.startPosition = new Position(0, 0);
        this.exitPosition = new Position(getRowsCount() - 1, getColsCount() - 1);
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

    public boolean isExit(int x, int y) {
        return x == exitPosition.getX() && y == exitPosition.getY();
    }

    private boolean isValidBounds(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public Position getStartPosition() { return startPosition; }
    public Position getExitPosition() { return exitPosition; }
    public int getRowsCount() { return grid.length; }
    public int getColsCount() { return grid[0].length; }
}