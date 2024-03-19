package Commands;

import utility.Console;
import Exception.*;

import java.io.FileNotFoundException;

public class ExecuteScriptCommand extends Commands {
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. ");
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if (argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Выполняю скрипт '" + argument[1] + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + " [file_name]");
        }
        return true;
    }
}
