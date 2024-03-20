package Commands;

import Manager.*;
import utility.*;
import Exception.*;
import Data.*;
import utility.Asker;

/**
 * This command add new dragon if it's age is greater than maximum age's dragons in the collection
 */
public class AddIfMaxCommand extends Commands {
    private final DragonManager dragonManager;
    public AddIfMaxCommand(DragonManager dragonManager) {
        super("add_if_max", "добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
        this.dragonManager = dragonManager;
    }

    /**
     * Find the maximum age's dragon in the collection
     * @return maximum age's dragon in the collection
     */
    private int getMaxAge() {
        int maxAge = dragonManager.getFirstDragon().getAge();
        for ( Dragon dragon : dragonManager.getCollection() ){
            if ( maxAge < dragon.getAge() )  maxAge = dragon.getAge();
        }
        return maxAge;
    }

    /**
     * Check if the collection is empty
     * @return yes/no
     */
    private boolean checkIfCollectionIsEmpty() {
        if ( dragonManager.getCollectionSize() == 0 ){
            Console.printError("Коллекция пуста!");
            return true;
        }
        return false;
    }

    @Override
    public boolean execute ( String[] argument ) {
        if ( checkIfCollectionIsEmpty() ) return true;
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            Console.println("* Создание нового продукта (только успешно если это максимально):");
            Dragon newDragon = Asker.buildDragon();

            if ( newDragon.getAge() > getMaxAge() ) {
                dragonManager.addToCollection( newDragon );
                Console.println("Дракон успешно добавлен!");
            } else throw new IsNotExtremeException();
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        } catch ( IsNotExtremeException e ) {
            Console.println("Неуспешно! Значение нового дракона меньше, чем значение наибольшего дракона! (< " + getMaxAge() +")");
        }
        return false;
    }



}
