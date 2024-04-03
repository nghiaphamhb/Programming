package Commands;

import Manager.DragonManager;
import Utility.*;
import Utility.Input.Input;

/**
 * Команда для добавления дракона в коллекцию
 */
public class AddCommand extends Commands {
    private DragonManager dragonManager;
    private Input input;
    public AddCommand( DragonManager dragonManager, Input input ) {
        super("add", "добавить новый элемент в коллекцию");
        this.dragonManager = dragonManager;
        this.input = input;
    }


    @Override
    public boolean execute( String[] argument ) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            Display.println("* Создание нового дракона:");
            dragonManager.addToCollection( input.buildDragon() );
            Display.println("Дракон успешно добавлен!");
            return true;

        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
