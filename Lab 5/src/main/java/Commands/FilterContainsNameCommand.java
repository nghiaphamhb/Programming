package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Exception.*;
import utility.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Thís command prints information's dragon by the name
 */
public class FilterContainsNameCommand extends Commands {
    private final DragonManager dragonManager;
    public FilterContainsNameCommand( DragonManager dragonManager) {
        super("filter_contains_name", "вывести элементы, значение поля name " +
                "которых содержит заданную подстроку");
        this.dragonManager = dragonManager;
    }

    /**
     * Create a list of dragons, who has that name
     * @param name name to find
     * @return list of dragons
     */
    private List<Dragon> filterByName( String name ) {
        List<Dragon> dragonList = new ArrayList<>();
        for ( Dragon d : dragonManager.getCollection() ) {
            if ( d.getName().equals(name) ) dragonList.add(d);
        }
        return dragonList;

    }

    @Override
    public boolean execute (String[] argument) {
        try {
            if ( argument[1].isEmpty() ) throw new IllegalArgumentException();
            List<Dragon> dragonHasName = filterByName(argument[1]);

            if ( dragonHasName.isEmpty() ) {
                Console.println("Драконов, чьи name содержат '" + argument[1] + "' не обнаружено.");
            } else {
                Console.println("Драконов, чьи name содержат '" + argument[1] + "' обнаружено " + dragonHasName.size() + " шт.\n");
                for ( Dragon d : dragonHasName ){
                    Console.println(d);
                }
            }
            return true;
        } catch (IllegalArgumentException exception) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + " [name]");
        }
        return false;
    }

}
