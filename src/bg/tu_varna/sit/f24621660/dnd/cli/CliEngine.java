package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;

import java.util.Scanner;

public class CliEngine {
    private final GameContext context;
    private final CommandParser parser;
    private final Scanner scanner;

    public CliEngine(GameContext context, CommandParser parser, Scanner scanner) {
        this.context = context;
        this.parser = parser;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            if (context.getStateManager().getCurrent() == State.GAME_OVER) {
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