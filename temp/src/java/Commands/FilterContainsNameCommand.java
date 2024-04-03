package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Utility.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда для показания информации у дракона, имя которого указано
 */
public class FilterContainsNameCommand extends Commands {
    private final DragonManager dragonManager;
    public FilterContainsNameCommand( DragonManager dragonManager) {
        super("filter_contains_name", "вывести элементы, значение поля name " +
                "которых содержит заданную подстроку");
        this.dragonManager = dragonManager;
    }

    /**
     * Собирать список драконов, имя которых указано
     * @param name указанное имя
     * @return список драконов
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
                Display.println("Драконов, чьи name содержат '" + argument[1] + "' не обнаружено.");
            } else {
                Display.println("Драконов, чьи name содержат '" + argument[1] + "' обнаружено " + dragonHasName.size() + " шт.\n");
                for ( Dragon d : dragonHasName ){
                    Display.println(d);
                }
            }
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + " [name]");
        }
        return false;
    }

}
