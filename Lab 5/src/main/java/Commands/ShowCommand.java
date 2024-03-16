package Commands;
import Data.*;
import Manager.DragonManager;
import utility.Console;

public class ShowCommand extends Commands {
    private DragonManager dragonManager;
    public ShowCommand(DragonManager dragonManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute(String[] argument) {
        if (!argument[1].isEmpty()) {
            Console.println("Использование: " + getName() );
            return false;
        }
        Console.println(dragonManager);
        return true;
    }
}
