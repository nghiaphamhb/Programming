package Commands;

import utility.Console;
import Exception.*;

public class ExitCommand extends Commands {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Программ закончен.");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
