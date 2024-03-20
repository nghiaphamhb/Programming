package Commands;


import Manager.DragonManager;
import utility.*;
import Exception.*;
import utility.Asker;

/**
 * Command to add new dragon into collection
 */
public class AddCommand extends Commands {
    private final DragonManager dragonManager;
    public AddCommand( DragonManager dragonManager ) {
        super("add", "добавить новый элемент в коллекцию");
        this.dragonManager = dragonManager;
    }


    @Override
    public boolean execute( String[] argument ) {
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            Console.println("* Создание нового дракона:");
            dragonManager.addToCollection( Asker.buildDragon() );
            Console.println("Дракон успешно добавлен!");
            return true;

        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
