package bg.tu_varna.sit.f24621660.dnd.world.models.map;

public class GameMap {
    public static final char WALL_SYMBOL = '#';
    public static final char PATH_SYMBOL = '.';

    private final char[][] grid;

    public GameMap(char[][] grid) {
        if (grid == null || grid.length == 0) {
            throw new IllegalArgumentException("Map grid cannot be null or empty.");
        }

        this.grid = new char[grid.length][];

        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = grid[i].clone();
        }
    }

    public void setCell(Position position, char symbol) {
        if (isValidBounds(position)) {
            grid[position.x()][position.y()] = symbol;
        }
    }

    public char getCell(Position position) {
        if (isValidBounds(position)) {
            return grid[position.x()][position.y()];
        }
        return WALL_SYMBOL;
    }

    public boolean isWalkable(Position position) {
        return isValidBounds(position) && getCell(position) != WALL_SYMBOL;
    }

    private boolean isValidBounds(Position position) {
        return position.x() >= 0 && position.x() < grid.length &&
                position.y() >= 0 && position.y() < grid[0].length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                builder.append(cell).append("  ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public int getRowsCount() { return grid.length; }
    public int getColsCount() { return grid[0].length; }
}