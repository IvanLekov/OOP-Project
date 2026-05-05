package bg.tu_varna.sit.f24621660.dnd.world.models.level;

import bg.tu_varna.sit.f24621660.dnd.items.ItemTable;
import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

public class LevelData {
    private final GameMap map;
    private final GameMapManager mapManager;
    private final ItemTable itemTable;

    public LevelData(GameMap map, GameMapManager mapManager, ItemTable itemTable) {
        this.map = map;
        this.mapManager = mapManager;
        this.itemTable = itemTable;
    }

    public GameMap getMap() { return map; }
    public GameMapManager getMapManager() { return mapManager; }
    public ItemTable getItemTable() { return itemTable; }
}