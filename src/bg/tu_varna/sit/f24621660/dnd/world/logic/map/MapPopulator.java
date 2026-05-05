package bg.tu_varna.sit.f24621660.dnd.world.logic.map;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

import java.util.Random;


public class MapPopulator {
    private final Random random;

    public MapPopulator() {
        this.random = new Random();
    }

    public void populate(GameMap gameMap, int monstersCount, int treasuresCount) {
        placeEntities(gameMap, 'M', monstersCount);
        placeEntities(gameMap, 'T', treasuresCount);
    }

    private void placeEntities(GameMap gameMap, char symbol, int count) {
        int placed = 0;
        int maxRow = gameMap.getRowsCount() - 1;
        int maxCol = gameMap.getColsCount() - 1;

        while (placed < count) {
            int randomX = random.nextInt(gameMap.getRowsCount());
            int randomY = random.nextInt(gameMap.getColsCount());

            boolean isStart = (randomX == 0 && randomY == 0);
            boolean isExit = (randomX == maxRow && randomY == maxCol);

            if (gameMap.isWalkable(randomX, randomY)
                    && gameMap.getCell(randomX, randomY) == '.'
                    && !isStart
                    && !isExit) {

                gameMap.setCell(randomX, randomY, symbol);
                placed++;
            }
        }
    }
}