package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Utility.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда для показания списки драконов со своей информацией в порядке убывания возраста
 */
public class PrintDescendingCommand extends Commands {
    private DragonManager dragonManager;

    public PrintDescendingCommand(DragonManager dragonManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.dragonManager = dragonManager;
    }

    /**
     * Создать список драконов в порядке убывания возраста
     * @param dragonManager данная коллекция
     * @return список драконов
     */
    private List<Dragon> sortDragonsByAge(DragonManager dragonManager) {
        List<Dragon> dragonList = new ArrayList<>(dragonManager.getCollection());
        for (int i = dragonList.size() - 1; i > 0; i--){    //algorithm buble sort
            for (int j = 0; j < i; j++){
                if (dragonList.get(j).getAge() < dragonList.get(j + 1).getAge()){
                    Dragon temp = dragonList.get(j);
                    dragonList.set(j, dragonList.get(j+1));
                    dragonList.set(j+1, temp);
                }
            }
        }
        return dragonList;
    }

    @Override
    public boolean execute(String[] argument) {
        try{
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException ();
            List<Dragon> listByAge = sortDragonsByAge(dragonManager);
            for (Dragon dragon : listByAge){
                Display.println(dragon);
            }
        } catch (IllegalArgumentException e){
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return true;
    }
}
