package Commands;

import Manager.*;
import utility.*;
import Exception.*;
import Data.*;
import utility.Asker;

public class AddIfMaxCommand extends Commands {
    private final DragonManager dragonManager; //khi nào thì ko dùng final; ví dụ 1 object có biến tọa độ thay doi
    public AddIfMaxCommand(DragonManager dragonManager) {
        super("add_if_max", "добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute (String[] argument) {
        if (!checkCollectionSize()) return true;
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("* Создание нового продукта (только успешно если это максимально):");
            Dragon newDragon = Asker.buildDragon();

            if (newDragon.getAge() > getMaxAge()) {
                dragonManager.addToCollection(newDragon);
                Console.println("Дракон успешно добавлен!");
            } else {
                Console.println("Неуспешно! Значение нового дракона меньше, чем значение наибольшего дракона!(" + newDragon.getAge() + " < " + getMaxAge() +")");
            }
            return true;

        } catch (WrongAmountOfElementsException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'"); }

        return false;
    }

    private int getMaxAge() {
        int maxAge = dragonManager.getFirstDragon().getAge();
        for (Dragon d : dragonManager.getCollection() ){
            if ( maxAge < d.getAge() )  maxAge = d.getAge();
        }
        return maxAge;
    }
    private boolean checkCollectionSize (){
        if (dragonManager.getCollectionSize() == 0){
            Console.printError("Коллекция пуста!");
            return false;
        }
        return true;
    }

}
