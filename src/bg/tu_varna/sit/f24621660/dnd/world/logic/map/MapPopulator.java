package bg.tu_varna.sit.f24621660.dnd.world.logic.map;

import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;

import java.util.Random;

public class MapPopulator {
    private final Random random;

    public MapPopulator(Random random) {
        this.random = random;
    }

    public void populate(GameMap gameMap, int monstersCount, int treasuresCount) {

        placeEntities(gameMap, GameMapManager.MONSTER_SYMBOL, monstersCount);
        placeEntities(gameMap, GameMapManager.TREASURE_SYMBOL, treasuresCount);
    }

    private void placeEntities(GameMap gameMap, char symbol, int count) {
        int placed = 0;

        Position startPos = new Position(0, 0);
        Position exitPos = new Position(gameMap.getRowsCount() - 1, gameMap.getColsCount() - 1);

        int maxAttempts = count * 10;
        int attempts = 0;

        while (placed < count && attempts < maxAttempts) {
            attempts++;

            int randomX = random.nextInt(gameMap.getRowsCount());
            int randomY = random.nextInt(gameMap.getColsCount());
            Position randomPos = new Position(randomX, randomY);

            boolean isStart = randomPos.equals(startPos);
            boolean isExit = randomPos.equals(exitPos);

            if (gameMap.isWalkable(randomPos)
                    && gameMap.getCell(randomPos) == GameMap.PATH_SYMBOL
                    && !isStart
                    && !isExit) {

                gameMap.setCell(randomPos, symbol);
                placed++;
            }
        }
    }
}