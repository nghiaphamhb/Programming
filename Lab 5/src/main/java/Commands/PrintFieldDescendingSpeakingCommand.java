package Commands;

import Data.Dragon;
import Manager.DragonManager;
import utility.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * This command prints all dragons of the collection in the descending order of ability speak
 */
public class PrintFieldDescendingSpeakingCommand extends Commands {
    private DragonManager dragonManager;
    public PrintFieldDescendingSpeakingCommand( DragonManager dragonManager ) {
        super("print_field_descending_speaking", "вывести значения поля speaking всех элементов в порядке убывания");
        this.dragonManager = dragonManager;
    }

    /**
     * Print name's dragon and its ability speak
     * @param listDragon list of dragons
     */
    private void print_Name_Speaking ( List<Dragon> listDragon) {
        for ( Dragon dragon : listDragon ) {
            Console.println( dragon.getName() + " -> " + dragon.getSpeaking() );
        }
    }

    @Override
    public boolean execute(String[] argument) {
        try{
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();

            List<Dragon> dragons_1 = new ArrayList<>();
            List<Dragon> dragons_2 = new ArrayList<>();

            for ( Dragon dragon : dragonManager.getCollection() ) {
                if ( dragon.getSpeaking() ) dragons_1.add( dragon );
                else dragons_2.add( dragon);
            }
            dragons_1.addAll( dragons_2 );

            print_Name_Speaking( dragons_1 );
            return true;
        } catch ( IllegalArgumentException e ) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }


}
