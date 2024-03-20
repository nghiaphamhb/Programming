package Commands;

import Data.Dragon;
import Manager.DragonManager;
import utility.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * This command prints all dragons in the collection in descending order of age
 */
public class PrintDescendingCommand extends Commands {
    private DragonManager dragonManager;

    public PrintDescendingCommand(DragonManager dragonManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.dragonManager = dragonManager;
    }

    /**
     * Create a list of dragon, where all dragons are sorted in descending order of age
     * @param dragonManager collection of dragons
     * @return new list of dragon, that was sorted
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
                Console.println(dragon);
            }
        } catch (IllegalArgumentException e){
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return true;
    }
}
