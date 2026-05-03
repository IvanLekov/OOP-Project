package bg.tu_varna.sit.f24621660.dnd.world.logic;
import bg.tu_varna.sit.f24621660.dnd.world.models.GameMap;

import java.util.Random;


public class WorldPopulator {
    private final Random random;

    public WorldPopulator() {
        this.random = new Random();
    }

    public void populate(GameMap gameMap, int monstersCount, int treasuresCount) {
        placeEntities(gameMap, 'M', monstersCount);
        placeEntities(gameMap, 'T', treasuresCount);
    }

    private void placeEntities(GameMap gameMap, char symbol, int count) {
        int placed = 0;

        while (placed < count) {

            int randomX = random.nextInt(gameMap.getRowsCount());
            int randomY = random.nextInt(gameMap.getColsCount());

            if (gameMap.isWalkable(randomX, randomY)
                    && gameMap.getCell(randomX, randomY) == '.'
                    && !(randomX == 0 && randomY == 0)) {

                gameMap.setCell(randomX, randomY, symbol);
                placed++;
            }
        }
    }
}