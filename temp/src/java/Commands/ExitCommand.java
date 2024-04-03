package Commands;

import Utility.Display;

/**
 * Команда для окончания программы
 */
public class ExitCommand extends Commands {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (!argument[1].isEmpty()) throw new IllegalArgumentException();
            Display.println("Программ закончен.");
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
