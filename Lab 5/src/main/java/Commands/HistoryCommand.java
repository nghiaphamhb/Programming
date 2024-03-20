package Commands;

import Manager.CommandManager;
import utility.Console;
import Exception.*;

import java.util.ArrayDeque;
import java.util.List;

/**
 * This command shows history of used commands (maximum recent 13 commands)
 */
public class HistoryCommand extends Commands {
    private final CommandManager commandManager;
    public HistoryCommand(CommandManager commandManager) {
        super("history", "вывести последние 13 команд (без их аргументов)");
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            ArrayDeque<String> history = commandManager.getCommandHistory();
            history.forEach(Console::println);
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;

    }
}
