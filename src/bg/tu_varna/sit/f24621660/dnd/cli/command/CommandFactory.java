package bg.tu_varna.sit.f24621660.dnd.cli.command;

import bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands.LoadGameCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands.SaveGameCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands.CombatStatusCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands.HelpCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands.NewGameCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.InventoryCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.StatsCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate.AllocateCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate.AllocateDoneCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack.AttackPowerCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack.AttackSpellCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot.LootDiscardCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot.LootEquipCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement.MoveDownCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement.MoveLeftCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement.MoveRightCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement.MoveUpCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.level.NextLevelCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands.ShowMapCommand;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<State, Map<String, Command>> stateCommands = new HashMap<>();

    private final Map<String, Command> globalCommands = new HashMap<>();


    public CommandFactory() {
        for (State state : State.values()) {
            stateCommands.put(state, new HashMap<>());
        }

        registerCommands();
    }

    private void registerCommands() {
        // --- Глобални команди (позволени винаги) ---
        globalCommands.put("help", new HelpCommand());
        globalCommands.put("stats", new StatsCommand());
        globalCommands.put("inventory", new InventoryCommand());
        globalCommands.put("save_game", new SaveGameCommand());
        globalCommands.put("load_game", new LoadGameCommand());
        // open/close/save/save as

        // --- EXPLORATION (---
        Map<String, Command> exploreCommands = stateCommands.get(State.EXPLORATION);
//        exploreCommands.put("new_game", new NewGameCommand());
        exploreCommands.put("show_map", new ShowMapCommand());
        exploreCommands.put("move up", new MoveUpCommand());
        exploreCommands.put("move down", new MoveDownCommand());
        exploreCommands.put("move left", new MoveLeftCommand());
        exploreCommands.put("move right", new MoveRightCommand());

        // ---  COMBAT ---
        Map<String, Command> combatCommands = stateCommands.get(State.COMBAT);
        combatCommands.put("attack power", new AttackPowerCommand());
        combatCommands.put("attack spell", new AttackSpellCommand());
        combatCommands.put("combat_status", new CombatStatusCommand());

        // --- LOOTING ---
        Map<String, Command> lootCommands = stateCommands.get(State.LOOTING);
        lootCommands.put("loot equip", new LootEquipCommand());
        lootCommands.put("loot discard", new LootDiscardCommand());

        // ---  LEVEL_UP ---
        Map<String, Command> levelUpCommands = stateCommands.get(State.LEVEL_UP);
        levelUpCommands.put("next_level", new NextLevelCommand());
        levelUpCommands.put("allocate", new AllocateCommand());
        levelUpCommands.put("allocate_done", new AllocateDoneCommand());
    }

    public Command getCommand(State currentState, String commandName) {
        if (globalCommands.containsKey(commandName)) {
            return globalCommands.get(commandName);
        }

        Map<String, Command> availableForState = stateCommands.get(currentState);
        return availableForState.get(commandName);
    }
}