package bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public class HelpCommand implements Command {

    public void printCommands() {

    }

    @Override
    public String execute(GameContext context, String[] args) {
        return """
                new_game <race>
                \
                load_game <file>
                \
                save_game <file>
                \
                exit
                \
                help
                \
                show_map
                \
                stats
                \
                inventory
                \
                load_level <level>
                \
                move up
                \
                move down
                \
                move left
                \
                move right
                \
                loot equip
                \
                loot discard
                \
                attack power
                \
                attack spell
                \
                combat_status
                \
                next_level
                \
                allocate <stat> <points>
                \
                allocate_done
                """;
    }
}
