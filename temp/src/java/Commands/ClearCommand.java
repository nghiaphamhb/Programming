package Commands;

import Manager.DragonManager;
import Utility.*;

/**
 * Команда для уборки данной коллекции
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
            Display.println("Коллекция очищена!");
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
