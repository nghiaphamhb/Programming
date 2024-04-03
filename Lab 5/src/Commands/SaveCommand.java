package Commands;

import Manager.DragonManager;
import Utility.Display;

/**
 * Сохранить данную коллекцию в файл
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
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: " + getName() );
        }
        return false;
    }
}
