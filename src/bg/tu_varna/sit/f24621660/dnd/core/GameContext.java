package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.combat.Battle;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.items.ItemTable;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.world.GameMapManager;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;

public class GameContext {

    private GameMap gameMap;
    private GameMapManager mapManager;
    private ItemTable itemTable;
    private Item currentLoot;

    private Battle currentBattle;

    private Hero hero;

    private boolean isLevelUpProcessed = false;


    private int tempStr = 0;
    private int tempMana = 0;
    private int tempHealth = 0;
    public void addTempStats(int str, int mana, int health) {
        this.tempStr += str;
        this.tempMana += mana;
        this.tempHealth += health;
    }
    public void clearTempStats() {
        this.tempStr = 0;
        this.tempMana = 0;
        this.tempHealth = 0;
    }
    public int getTempStr() { return tempStr; }
    public int getTempMana() { return tempMana; }
    public int getTempHealth() { return tempHealth; }
    public int getTotalTempStats() { return tempStr + tempMana + tempHealth; }

    public boolean isLevelUpProcessed() { return isLevelUpProcessed; }
    public void setLevelUpProcessed(boolean levelUpProcessed) { isLevelUpProcessed = levelUpProcessed; }

    public Item getCurrentLoot() { return currentLoot; }
    public void setCurrentLoot(Item currentLoot) { this.currentLoot = currentLoot; }
    public void clearCurrentLoot() { this.currentLoot = null; }

    public Battle getCurrentBattle() { return currentBattle; }
    public void setCurrentBattle(Battle currentBattle) { this.currentBattle = currentBattle; }
    public void clearCurrentBattle() { this.currentBattle = null; }

    public Hero getHero() { return hero; }
    public void setHero(Hero hero) { this.hero = hero; }

    public GameMap getGameMap() { return gameMap; }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap; }

    public GameMapManager getMapManager() { return mapManager; }
    public void setMapManager(GameMapManager mapManager) { this.mapManager = mapManager; }

    public ItemTable getItemTable() { return itemTable; }
    public void setItemTable(ItemTable itemTable) { this.itemTable = itemTable; }
}