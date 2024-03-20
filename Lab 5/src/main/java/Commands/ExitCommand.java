package Commands;

import utility.Console;
import Exception.*;

/**
 * Thís command close the program
 */
public class ExitCommand extends Commands {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (!argument[1].isEmpty()) throw new IllegalArgumentException();
            Console.println("Программ закончен.");
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
