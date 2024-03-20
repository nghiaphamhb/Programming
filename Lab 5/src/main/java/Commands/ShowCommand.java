package Commands;
import Data.*;
import Manager.DragonManager;
import utility.Console;
import Exception.*;

/**
 * This command shows all dragons in the collection
 */
public class ShowCommand extends Commands {
    private DragonManager dragonManager;
    public ShowCommand(DragonManager dragonManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute(String[] argument) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            Console.println(dragonManager);
            return true;
        } catch (IllegalArgumentException e) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: " + getName() );
        }
        return false;
    }
}
