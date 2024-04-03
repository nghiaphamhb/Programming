package Commands;

import Utility.Display;

/**
 * Команда для выполнения скрипты
 */
public class ExecuteScriptCommand extends Commands {
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. ");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (argument[1].isEmpty()) throw new IllegalArgumentException();
            Display.println("Выполняю скрипт '" + argument[1] + "'...");
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + " [file_name]");
        }
        return true;
    }
}
