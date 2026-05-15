package bg.tu_varna.sit.f24621660.dnd.world.models.level;

import bg.tu_varna.sit.f24621660.dnd.items.ItemTable;
import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

public record LevelData(GameMap map, GameMapManager mapManager, ItemTable itemTable) {
}