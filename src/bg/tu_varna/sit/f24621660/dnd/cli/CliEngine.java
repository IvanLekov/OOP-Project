package bg.tu_varna.sit.f24621660.dnd.cli;

// CliEngine.java
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

import java.util.Scanner;

public class CliEngine {
    private final GameContext context;
    private final CommandParser parser;
    private final Scanner scanner;

    public CliEngine() {
        this.context = new GameContext();
        this.parser = new CommandParser();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Добре дошли в D&D играта! Въведете команда (или 'exit' за изход):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("exit")) {
                System.out.println("Излизане от програмата...");
                break;
            }

            String outputMessage = parser.processInput(context, input);
            System.out.println(outputMessage);
        }
    }
}