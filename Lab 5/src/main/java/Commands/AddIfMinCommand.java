package Commands;

import Data.Dragon;
import Manager.*;
import Exception.*;
import utility.*;
/**
 * This command add new dragon if it's age is smaller than minimum age's dragons in the collection
 */
public class AddIfMinCommand extends Commands {
    private final DragonManager dragonManager;
    public AddIfMinCommand(DragonManager dragonManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.dragonManager = dragonManager;
    }

    /**
     * Find the minimum age's dragon in the collection
     * @return minimum age's dragon in the collection
     */
    private int getMinAge (){
        int minAge = dragonManager.getFirstDragon().getAge();
        for ( Dragon d : dragonManager.getCollection() ) {
            if ( minAge < d.getId() ) minAge = d.getAge();
        }
        return minAge;
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
    public boolean execute( String[] argument ) {
        if ( checkIfCollectionIsEmpty() ) return true;
        try {
            if (!argument[1].isEmpty()) throw new IllegalArgumentException();
            Console.println("* Создание нового продукта (только успешно если это минимально):");
            Dragon newDragon = Asker.buildDragon();

            if ( newDragon.getAge() < getMinAge() ) {
                dragonManager.addToCollection( newDragon );
                Console.println("Дракон успешно добавлен!");
            } else throw new IsNotExtremeException();
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        } catch ( IsNotExtremeException e ) {
            Console.println("Неуспешно! Значение нового дракона больше, чем значение наименьшего дракона! (> " + getMinAge() +")");
        }
        return false;
    }


}
