package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.core.enums.GameStatus;

public class GameState {
    private static GameState instance;
    private GameStatus currentStatus;

    private GameState() {
        this.currentStatus = GameStatus.EXPLORATION;
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public GameStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setStatus(GameStatus newStatus) {
        this.currentStatus = newStatus;
    }
}