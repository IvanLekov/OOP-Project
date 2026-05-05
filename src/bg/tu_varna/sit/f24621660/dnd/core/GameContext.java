package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.items.ItemTable;
import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

public class GameContext {

    private GameMap gameMap;
    private GameMapManager mapManager;
    private ItemTable itemTable;

    private Hero hero;



    public Hero getHero() { return hero; }
    public void setHero(Hero hero) { this.hero = hero; }

    public GameMap getGameMap() { return gameMap; }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap; }

    public GameMapManager getMapManager() { return mapManager; }
    public void setMapManager(GameMapManager mapManager) { this.mapManager = mapManager; }

    public ItemTable getItemTable() { return itemTable; }
    public void setItemTable(ItemTable itemTable) { this.itemTable = itemTable; }
}