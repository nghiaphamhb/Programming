package Commands;

import Manager.DragonManager;
import utility.Console;

/**
 * This command saves collection into file JSON
 */
public class SaveCommand extends Commands {
    private final DragonManager dragonManager;
    public SaveCommand( DragonManager dragonManager) {
        super("save", "сохранить коллекцию в файл");
        this.dragonManager = dragonManager;
    }


    @Override
    public boolean execute(String[] argument) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            dragonManager.saveCollection();
            return true;
        } catch (IllegalArgumentException e) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: " + getName() );
        }
        return false;
    }
}
