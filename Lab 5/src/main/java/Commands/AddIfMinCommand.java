package Commands;

import Data.Dragon;
import Manager.*;
import Exception.*;
import utility.*;

public class AddIfMinCommand extends Commands {
    private final DragonManager dragonManager;
    public AddIfMinCommand(DragonManager dragonManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute(String[] argument) {
        if (!checkCollectionSize()) return true;
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("* Создание нового продукта (только успешно если это минимально):");
            Dragon newDragon = Asker.buildDragon();

            if (newDragon.getAge() < getMinAge()) {
                dragonManager.addToCollection(newDragon);
                Console.println("Дракон успешно добавлен!");
            } else {
                Console.println("Неуспешно! Значение нового дракона больше, чем значение наименьшего дракона!(" + newDragon.getAge() + " > " + getMinAge() +")");
            }
            return true;

        } catch (WrongAmountOfElementsException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }

    private int getMinAge (){
        int minAge = dragonManager.getFirstDragon().getAge();
        for (Dragon d : dragonManager.getCollection() ){
            if (minAge < d.getId()) minAge = d.getAge();
        }
        return minAge;
    }
    private boolean checkCollectionSize (){
        if (dragonManager.getCollectionSize() == 0){
            Console.printError("Коллекция пуста!");
            return false;
        }
        return true;
    }
}
