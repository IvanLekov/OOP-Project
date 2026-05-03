package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class GameState {
    private static GameState instance;
    private State currentState;

    private GameState() {
        this.currentState = State.EXPLORATION;
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setState(State newStatus) {
        this.currentState = newStatus;
    }
}