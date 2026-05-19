package bg.tu_varna.sit.f24621660.dnd.cli.registry;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands.LoadGameCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.file_commands.SaveGameCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.game_commands.*;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.*;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.allocate.*;
// Забележи! Вече импортваме само базовите команди, вместо по 4 отделни за всяко действие
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack.AttackCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot.LootCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.movement.MoveCommand;
import bg.tu_varna.sit.f24621660.dnd.cli.command.level_commands.*;
import bg.tu_varna.sit.f24621660.dnd.cli.command.map_commands.ShowMapCommand;

import bg.tu_varna.sit.f24621660.dnd.io.game.GameStorage;
import bg.tu_varna.sit.f24621660.dnd.io.game.XmlGameStorage;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.ItemTableLoader;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.MapLoader;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.ItemParser;
import bg.tu_varna.sit.f24621660.dnd.io.parsers.MapParser;
import bg.tu_varna.sit.f24621660.dnd.io.readers.GameFileReader;
import bg.tu_varna.sit.f24621660.dnd.io.readers.TextFileReader;
import bg.tu_varna.sit.f24621660.dnd.items.ItemFactory;
import bg.tu_varna.sit.f24621660.dnd.world.logic.map.MapPopulator;
import bg.tu_varna.sit.f24621660.dnd.world.logic.level.LevelBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DependencyRegistry {

    private final LevelBuilder levelBuilder;
    private final GameFileReader reader;
    private final Random random;

    public DependencyRegistry() {
        this.random = new Random();
        this.reader = new TextFileReader();

        ItemFactory itemFactory = new ItemFactory();

        MapParser mapParser = new MapParser();
        ItemParser itemParser = new ItemParser(itemFactory);

        MapLoader mapLoader = new MapLoader(reader, mapParser);
        ItemTableLoader itemLoader = new ItemTableLoader(reader, itemParser);

        MapPopulator populator = new MapPopulator(random);
        this.levelBuilder = new LevelBuilder(mapLoader, populator, itemLoader, random);
    }

    public Map<String, Command> loadCommands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put("new_game", new NewGameCommand(levelBuilder));
        commands.put("next_level", new NextLevelCommand(levelBuilder));
        commands.put("load_level", new LoadLevelCommand(reader));
        commands.put("help", new HelpCommand());

        ShowMapCommand showMap = new ShowMapCommand();
        commands.put("show_map", showMap);
        commands.put("move", new MoveCommand(random, showMap));

        commands.put("stats", new StatsCommand());
        commands.put("inventory", new InventoryCommand());

        commands.put("allocate", new AllocateCommand());
        commands.put("allocate_done", new AllocateDoneCommand());

        GameStorage xmlStorage = new XmlGameStorage();
        commands.put("save_game", new SaveGameCommand(xmlStorage));
        commands.put("load_game", new LoadGameCommand(xmlStorage, levelBuilder));

        CombatStatusCommand combatStatus = new CombatStatusCommand();
        commands.put("combat_status", combatStatus);
        commands.put("attack", new AttackCommand(combatStatus));

        commands.put("loot", new LootCommand());

        return commands;
    }
}