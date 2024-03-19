package Commands;

import Data.Dragon;
import Manager.DragonManager;
import utility.Console;

import java.util.ArrayList;
import java.util.List;

public class PrintDescendingCommand extends Commands {
    private DragonManager dragonManager;
    private List<Dragon> dragons;

    public PrintDescendingCommand(DragonManager dragonManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.dragonManager = dragonManager;
    }



    @Override
    public boolean execute(String[] argument) {
        try{
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException ();
            dragons = new ArrayList<>(dragonManager.getCollection());
            int size = dragons.size();
            for (int i = size - 1; i > 0; i--){    //algorithm buble sort
                for (int j = 0; j < i; j++){
                    if (dragons.get(j).getAge() < dragons.get(j + 1).getAge()){
                        Dragon temp = dragons.get(j);
                        dragons.set(j, dragons.get(j+1));
                        dragons.set(j+1, temp);
                    }
                }
            }
            for (Dragon dragon : dragons){
                Console.println(dragon);
            }
        } catch (IllegalArgumentException e){
            Console.println("Использование: '" + getName() + "'");
        }

        return true;
    }
}
