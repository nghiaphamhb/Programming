package Commands;

import Manager.*;
import Utility.*;
import Exception.*;
import Data.*;
import Utility.Input.Input;

/**
 * Команда для добавления дракона в коллекцию при тем, что этот дракон считается самым старым в коллекции
 */
public class AddIfMaxCommand extends Commands {
    private DragonManager dragonManager;
    private Input input;

    public AddIfMaxCommand(DragonManager dragonManager, Input input) {
        super("add_if_max", "добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
        this.dragonManager = dragonManager;
        this.input = input;
    }

    /**
     * Найти наибольший возраст у дракона в коллекции
     * @return наибольший возраст
     */
    private int getMaxAge() {
        int maxAge = dragonManager.getFirstDragon().getAge();
        for ( Dragon dragon : dragonManager.getCollection() ){
            if ( maxAge < dragon.getAge() )  maxAge = dragon.getAge();
        }
        return maxAge;
    }

    /**
     * Проверить есть ли коллекция пуста
     * @return да/ нет
     */
    private boolean checkIfCollectionIsEmpty() {
        if ( dragonManager.getCollectionSize() == 0 ){
            Display.printError("Коллекция пуста!");
            return true;
        }
        return false;
    }

    @Override
    public boolean execute ( String[] argument ) {
        if ( checkIfCollectionIsEmpty() ) return true;
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            Display.println("* Создание нового продукта (только успешно если это максимально):");
            Dragon newDragon = input.buildDragon();

            if ( newDragon.getAge() > getMaxAge() ) {
                dragonManager.addToCollection( newDragon );
                Display.println("Дракон успешно добавлен!");
            } else throw new IsNotExtremeException();
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        } catch ( IsNotExtremeException e ) {
            Display.println("Неуспешно! Значение нового дракона меньше, чем значение наибольшего дракона! (< " + getMaxAge() +")");
        }
        return false;
    }



}
