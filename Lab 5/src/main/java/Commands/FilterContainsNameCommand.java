package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Exception.*;
import utility.Console;

import java.util.ArrayList;
import java.util.List;

public class FilterContainsNameCommand extends Commands {
    private final DragonManager dragonManager;
    public FilterContainsNameCommand( DragonManager dragonManager) {
        super("filter_contains_name", "вывести элементы, значение поля name " +
                "которых содержит заданную подстроку");
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute (String[] argument) {
        try {
            if (argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            List<Dragon> dragonHasName = filterByName(argument[1]);

            if (dragonHasName.isEmpty()) {
                Console.println("Драконов, чьи name содержат '" + argument[1] + "' не обнаружено.");
            } else {
                Console.println("Драконов, чьи name содержат '" + argument[1] + "' обнаружено " + dragonHasName.size() + " шт.\n");
                for ( Dragon d : dragonHasName){
                    Console.println(d);
                }
            }

            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + " [name]");
        }
        return false;
    }
    private List<Dragon> filterByName (String nameToFind){
        List<Dragon> dragonList = new ArrayList<>();
        for (Dragon d : dragonManager.getCollection()){
            if (d.getName().equals(nameToFind)) dragonList.add(d);
        }
        return dragonList;

    }
}
