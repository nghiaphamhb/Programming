package Commands;

import Data.Dragon;
import Manager.*;
import Exception.*;
import Utility.*;
import Utility.Input.Input;

/**
 * Команда для добавления дракона в коллекцию при тем, что этот дракон считается самым молодым в коллекции
 */
public class AddIfMinCommand extends Commands {
    private DragonManager dragonManager;
    private Input input;

    public AddIfMinCommand(DragonManager dragonManager, Input input) {
        super("add_if_min", "добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.dragonManager = dragonManager;
        this.input = input;
    }

    /**
     * Найти наименьший возраст у дракона в коллекции
     * @return наименьший возраст
     */
    private int getMinAge (){
        int minAge = dragonManager.getFirstDragon().getAge();
        for ( Dragon d : dragonManager.getCollection() ) {
            if ( minAge < d.getId() ) minAge = d.getAge();
        }
        return minAge;
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
    public boolean execute( String[] argument ) {
        if ( checkIfCollectionIsEmpty() ) return true;
        try {
            if (!argument[1].isEmpty()) throw new IllegalArgumentException();
            Display.println("* Создание нового продукта (только успешно если это минимально):");
            Dragon newDragon = input.buildDragon();

            if ( newDragon.getAge() < getMinAge() ) {
                dragonManager.addToCollection( newDragon );
                Display.println("Дракон успешно добавлен!");
            } else throw new IsNotExtremeException();
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        } catch ( IsNotExtremeException e ) {
            Display.println("Неуспешно! Значение нового дракона больше, чем значение наименьшего дракона! (> " + getMinAge() +")");
        }
        return false;
    }


}
