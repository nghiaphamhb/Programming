package Commands;

import Manager.DragonManager;
import Exception.*;
import utility.*;

public class ClearCommand extends Commands {
    private final DragonManager dragonManager;
    public ClearCommand(DragonManager dragonManager) {
        super("clear", "очистить коллекцию");
        this.dragonManager = dragonManager;
    }
    @Override
    public boolean execute(String[] argument){
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            dragonManager.clearCollection();
            Console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
