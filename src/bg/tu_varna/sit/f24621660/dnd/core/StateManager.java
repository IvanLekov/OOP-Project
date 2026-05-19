package bg.tu_varna.sit.f24621660.dnd.core;

import bg.tu_varna.sit.f24621660.dnd.core.states.State;

public class StateManager {
    private State currentState;

    public StateManager() {
        this.currentState = State.EXPLORATION;
    }

    public State getCurrent() {
        return currentState;
    }

    public void changeTo(State newState) {
        this.currentState = newState;
    }
}