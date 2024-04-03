package Commands;
import Manager.DragonManager;
import Utility.Display;

/**
 * Показать информации у всех драконов в данной коллекции
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
            Display.println(dragonManager);
            return true;
        } catch (IllegalArgumentException e) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: " + getName() );
        }
        return false;
    }
}
