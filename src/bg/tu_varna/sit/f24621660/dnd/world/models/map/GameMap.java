package bg.tu_varna.sit.f24621660.dnd.world.models.map;



public class GameMap {
    private final char[][] grid;

    public GameMap(char[][] grid) {
        this.grid = grid;
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
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                builder.append(grid[row][col]).append("  ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }


    public int getRowsCount() { return grid.length; }
    public int getColsCount() { return grid[0].length; }
}