package bg.tu_varna.sit.f24621660.dnd.world;

import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.InteractionType;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;

public class GameMapManager {
    private final GameMap gameMap;
    private Position heroPosition;



    public GameMapManager(GameMap gameMap) {
        this.gameMap = gameMap;

        this.heroPosition = new Position(0, 0);

        gameMap.setCell(0, 0, 'H');
    }

    public InteractionType moveHero(int dx, int dy) {
        int newX = heroPosition.getX() + dx;
        int newY = heroPosition.getY() + dy;

        if (!gameMap.isWalkable(newX, newY)) {
            return InteractionType.WALL;
        }

        InteractionType interaction = handleCellInteraction(newX, newY);

        gameMap.setCell(heroPosition.getX(), heroPosition.getY(), '.');

        heroPosition.setX(newX);
        heroPosition.setY(newY);

        gameMap.setCell(newX, newY, 'H');

        return interaction;
    }


    private InteractionType handleCellInteraction(int x, int y) {
        if (x == gameMap.getRowsCount() - 1 && y == gameMap.getColsCount() - 1) {
            return InteractionType.EXIT;
        }

        char cellSymbol = gameMap.getCell(x, y);
        return switch (cellSymbol) {
            case 'M' -> InteractionType.MONSTER;
            case 'T' -> InteractionType.TREASURE;
            default -> InteractionType.PATH;
        };
    }
    public Position getHeroPosition() {
        return heroPosition;
    }
}