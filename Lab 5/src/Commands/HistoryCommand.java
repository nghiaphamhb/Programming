package Commands;

import Manager.CommandManager;
import Utility.Display;

import java.util.ArrayDeque;

/**
 * Команда для показания истории использованных команд (максимально только показать 13 последние команды)
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
            history.forEach(Display::println);
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return false;

    }
}
