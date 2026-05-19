package bg.tu_varna.sit.f24621660.dnd;

import bg.tu_varna.sit.f24621660.dnd.cli.CliEngine;
import bg.tu_varna.sit.f24621660.dnd.cli.CommandParser;
import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.cli.command.CommandFactory;
import bg.tu_varna.sit.f24621660.dnd.cli.registry.DependencyRegistry;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.StateManager;

import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        StateManager stateManager = new StateManager();
        GameContext context = new GameContext(null, stateManager);

        DependencyRegistry registry = new DependencyRegistry();
        Map<String, Command> commandsMap = registry.loadCommands();

        CommandFactory commandFactory = new CommandFactory(commandsMap);

        CommandParser parser = new CommandParser(commandFactory);

        Scanner scanner = new Scanner(System.in);

        CliEngine cli = new CliEngine(context, parser, scanner);

        cli.run();
    }
}