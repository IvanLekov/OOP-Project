package bg.tu_varna.sit.f24621660.dnd.cli;

import bg.tu_varna.sit.f24621660.dnd.cli.command.CommandFactory;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

import java.util.Scanner;

public class CliEngine {
    private final GameContext context;
    private final CommandParser parser;
    private final Scanner scanner;

    public CliEngine() {
        this.context = new GameContext();
        CommandFactory factory = new CommandFactory();
        this.parser = new CommandParser(factory);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("exit")) break;

            String outputMessage = parser.processInput(context, input);
            System.out.println(outputMessage);
        }
    }
}