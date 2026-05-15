package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.cli.command.CommandFactory;
import bg.tu_varna.sit.f24621660.dnd.cli.registry.DependencyRegistry;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

import java.util.Scanner;

public class CliEngine {
    private final GameContext context;
    private final CommandParser parser;
    private final Scanner scanner;

    public CliEngine() {
        this.context = new GameContext();

        DependencyRegistry registry = new DependencyRegistry();
        CommandFactory factory = new CommandFactory(registry.loadCommands());

        this.parser = new CommandParser(factory);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {

            if (GameState.current() == State.GAME_OVER) {
                System.out.println("GAME OVER");
                System.out.println("Restart the application to try again.");
                break;
            }

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                break;
            }

            String outputMessage = parser.processInput(context, input);
            System.out.println(outputMessage);
        }
    }

}