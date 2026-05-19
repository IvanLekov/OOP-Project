package bg.tu_varna.sit.f24621660.dnd.world;

import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.InteractionType;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;

public class GameMapManager {
    public static final char HERO_SYMBOL = 'H';
    public static final char MONSTER_SYMBOL = 'M';
    public static final char TREASURE_SYMBOL = 'T';

    private final GameMap gameMap;
    private Position heroPosition;

    private final Position exitPosition;

    public GameMapManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.heroPosition = new Position(0, 0);
        this.gameMap.setCell(this.heroPosition, HERO_SYMBOL);

        this.exitPosition = new Position(gameMap.getRowsCount() - 1, gameMap.getColsCount() - 1);
    }

    public InteractionType moveHero(int dx, int dy) {
        Position newPosition = new Position(heroPosition.x() + dx, heroPosition.y() + dy);

        if (!gameMap.isWalkable(newPosition)) {
            return InteractionType.WALL;
        }

        InteractionType interaction = handleCellInteraction(newPosition);

        gameMap.setCell(heroPosition, GameMap.PATH_SYMBOL);
        heroPosition = newPosition;

        gameMap.setCell(heroPosition, HERO_SYMBOL);

        return interaction;
    }

    private InteractionType handleCellInteraction(Position position) {
        if (position.equals(exitPosition)) {
            return InteractionType.EXIT;
        }

        char cellSymbol = gameMap.getCell(position);
        return switch (cellSymbol) {
            case MONSTER_SYMBOL -> InteractionType.MONSTER;
            case TREASURE_SYMBOL -> InteractionType.TREASURE;
            default -> InteractionType.PATH;
        };
    }

    public Position getHeroPosition() {
        return heroPosition;
    }

    //--------Load Game Specific Methods--------

    public void restoreMapLayout(java.util.List<String> rows) {
        for (int r = 0; r < rows.size(); r++) {
            String row = rows.get(r);
            for (int c = 0; c < row.length(); c++) {
                this.gameMap.setCell(new Position(r, c), row.charAt(c));
            }
        }
    }

    public void teleportHero(Position newPosition) {
        gameMap.setCell(this.heroPosition, bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap.PATH_SYMBOL);
        this.heroPosition = newPosition;
        gameMap.setCell(this.heroPosition, HERO_SYMBOL);
    }
}