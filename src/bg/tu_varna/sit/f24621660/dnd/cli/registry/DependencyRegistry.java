package bg.tu_varna.sit.f24621660.dnd.cli.registry;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
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
import bg.tu_varna.sit.f24621660.dnd.cli.command.level.LoadLevelCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.level.NextLevelCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands.ShowMapCommand;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.ItemTableLoader;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.MapLoader;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemParser;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.MapParser;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;
import bg.tu_varna.sit.f24621660.dnd.world.logic.map.MapPopulator;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;

import java.util.HashMap;
import java.util.Map;

public class DependencyRegistry {

    private final LevelBuilder levelBuilder;
    private final GameFileReader reader = new TextFileReader();

    public DependencyRegistry() {
        MapParser mapParser = new MapParser();
        ItemParser itemParser = new ItemParser();

        MapLoader mapLoader = new MapLoader(reader, mapParser);
        ItemTableLoader itemLoader = new ItemTableLoader(reader, itemParser);

        MapPopulator populator = new MapPopulator();
        this.levelBuilder = new LevelBuilder(mapLoader, populator, itemLoader);
    }

    public Map<String, Command> loadCommands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put("new_game", new NewGameCommand(levelBuilder));
        commands.put("next_level", new NextLevelCommand(levelBuilder));

        commands.put("show_map", new ShowMapCommand());

        commands.put("load_level", new LoadLevelCommand(reader));

        commands.put("attack power", new AttackPowerCommand());
        commands.put("attack spell", new AttackSpellCommand());

        commands.put("combat_status", new CombatStatusCommand());

        commands.put("allocate", new AllocateCommand());
        commands.put("allocate_done", new AllocateDoneCommand());


        commands.put("loot equip", new LootEquipCommand());
        commands.put("loot discard", new LootDiscardCommand());

        commands.put("stats", new StatsCommand());
        commands.put("inventory", new InventoryCommand());

        commands.put("help", new HelpCommand());

        commands.put("move up", new MoveUpCommand());
        commands.put("move down", new MoveDownCommand());
        commands.put("move left", new MoveLeftCommand());
        commands.put("move right", new MoveRightCommand());

        return commands;
    }
}