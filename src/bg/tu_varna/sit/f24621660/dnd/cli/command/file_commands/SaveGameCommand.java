package bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.io.game.GameStorage;

public class SaveGameCommand implements Command {
    private final GameStorage storage;

    public SaveGameCommand(GameStorage storage) {
        this.storage = storage;
    }

    @Override
    public String execute(GameContext context, String[] args) {
        if (context.getHero() == null) {
            return "No active game to save. Start a new game first.";
        }

        if (args.length == 0) {
            return "Usage: save_game <file_name>";
        }

        String fileName = args[0];

        try {
            storage.save(context, fileName);
            return "Game successfully saved to '" + fileName + "'!";
        } catch (Exception e) {
            return "Error while saving game: " + e.getMessage();
        }
    }
}