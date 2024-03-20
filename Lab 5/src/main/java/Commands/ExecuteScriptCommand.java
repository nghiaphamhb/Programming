package Commands;

import utility.Console;
import Exception.*;

import java.io.FileNotFoundException;

/**
 * Thís command runs file script
 */
public class ExecuteScriptCommand extends Commands {
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. ");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (argument[1].isEmpty()) throw new IllegalArgumentException();
            Console.println("Выполняю скрипт '" + argument[1] + "'...");
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + " [file_name]");
        }
        return true;
    }
}
