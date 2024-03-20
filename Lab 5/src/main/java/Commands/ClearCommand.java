package Commands;

import Manager.DragonManager;
import Exception.*;
import utility.*;

/**
 * This command clears the collection
 */
public class ClearCommand extends Commands {
    private final DragonManager dragonManager;
    public ClearCommand( DragonManager dragonManager ) {
        super("clear", "очистить коллекцию");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute( String[] argument ) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            dragonManager.clearCollection();
            Console.println("Коллекция очищена!");
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
