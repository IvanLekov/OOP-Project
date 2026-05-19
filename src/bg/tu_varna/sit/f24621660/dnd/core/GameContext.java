package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.combat.models.Battle;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelUpSession;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.world.models.level.LevelData;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;

public class GameContext {

    private final StateManager stateManager;
    private Hero hero;

    private java.util.List<String> savedMapRows;
    private Integer savedLevelIndex;
    private Position savedHeroPosition;

    private LevelUpSession currentLevelUpSession;
    private LevelData currentLevel;

    private Battle currentBattle;
    private Item currentLoot;

    public GameContext(Hero hero, StateManager stateManager) {
        this.hero = hero;
        this.stateManager = stateManager;
    }


    public void loadLevel(LevelData levelData) { this.currentLevel = levelData; }
    public LevelData getCurrentLevel() { return currentLevel; }
    public Hero getHero() { return hero; }
    public void setHero(Hero hero) {  this.hero = hero; }
    public StateManager getStateManager() { return stateManager; }

    // --- ACTIVE EVENT HANDLERS---

    public LevelUpSession getLevelUpSession() { return currentLevelUpSession; }
    public void startLevelUpSession() { this.currentLevelUpSession = new LevelUpSession(); }
    public void clearLevelUpSession() { this.currentLevelUpSession = null; }
    public Battle getCurrentBattle() { return currentBattle; }
    public void startBattle(Battle battle) { this.currentBattle = battle; }
    public void clearBattle() { this.currentBattle = null; }
    public Item getCurrentLoot() { return currentLoot; }
    public void setLoot(Item item) { this.currentLoot = item; }
    public void clearLoot() { this.currentLoot = null; }

    // -- Load Level Specific---


    public void setSavedMapData(int levelIndex, Position position, java.util.List<String> mapRows) {
        this.savedLevelIndex = levelIndex;
        this.savedHeroPosition = position;
        this.savedMapRows = mapRows;
    }

    public Integer getSavedLevelIndex() { return savedLevelIndex; }
    public Position getSavedHeroPosition() { return savedHeroPosition; }
    public java.util.List<String> getSavedMapRows() { return savedMapRows; }

    public void clearSavedMapData() {
        this.savedLevelIndex = null;
        this.savedHeroPosition = null;
        this.savedMapRows = null;
    }
}