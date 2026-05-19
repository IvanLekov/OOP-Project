package bg.tu_varna.sit.f24621660.dnd.world.logic.level;

import bg.tu_varna.sit.f24621660.dnd.io.loaders.ItemTableLoader;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.MapLoader;
import bg.tu_varna.sit.f24621660.dnd.items.ItemTable;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.logic.map.MapPopulator;
import bg.tu_varna.sit.f24621660.dnd.world.models.level.LevelData;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

import java.util.List;
import java.util.Random;

public class LevelBuilder {
    private static final int LEVEL_ONE_MONSTER_COUNT = 2;
    private static final int LEVEL_TWO_MONSTER_COUNT = 3;
    private static final int LEVEL_ONE_TREASURE_COUNT = 2;
    private static final int LEVEL_TWO_TREASURE_COUNT = 2;

    private static final String MAPS_PATH_PREFIX = "resources/map_level_";
    private static final String MAPS_PATH_SUFFIX = ".txt";
    private static final String ITEMS_PATH = "resources/items.txt";

    private final MapLoader mapLoader;
    private final MapPopulator populator;
    private final ItemTableLoader itemLoader;
    private final Random random;

    public LevelBuilder(MapLoader mapLoader, MapPopulator populator, ItemTableLoader itemLoader, Random random) {
        this.mapLoader = mapLoader;
        this.populator = populator;
        this.itemLoader = itemLoader;
        this.random = random;
    }

    public LevelData buildLevel(int level) {
        String mapFile = MAPS_PATH_PREFIX + level + MAPS_PATH_SUFFIX;
        GameMap map = mapLoader.load(mapFile);

        int monstersCount = calculateEntitiesCount(level, LEVEL_ONE_MONSTER_COUNT, LEVEL_TWO_MONSTER_COUNT);
        int treasuresCount = calculateEntitiesCount(level, LEVEL_ONE_TREASURE_COUNT, LEVEL_TWO_TREASURE_COUNT);
        populator.populate(map, monstersCount, treasuresCount);

        GameMapManager mapManager = new GameMapManager(map);

        List<Item> levelItems = itemLoader.load(ITEMS_PATH, level);
        ItemTable itemTable = new ItemTable(levelItems, random);

        return new LevelData(map, mapManager, itemTable);
    }

    private int calculateEntitiesCount(int level, int level1Count, int level2Count) {
        if (level <= 1) return level1Count;
        if (level == 2) return level2Count;

        int prevPrev = level1Count;
        int prev = level2Count;
        int current = 0;

        for (int i = 3; i <= level; i++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }
        return current;
    }
}