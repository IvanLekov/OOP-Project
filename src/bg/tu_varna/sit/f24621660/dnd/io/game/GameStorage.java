package bg.tu_varna.sit.f24621660.dnd.io.game;

import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public interface GameStorage {
    void save(GameContext context, String fileName) throws Exception;
    void load(GameContext context, String fileName) throws Exception;
}