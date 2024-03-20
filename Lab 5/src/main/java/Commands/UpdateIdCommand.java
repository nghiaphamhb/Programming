package Commands;

import Data.*;
import Manager.*;
import utility.*;
import Exception.*;

/**
 * This command updates information of dragon by its ID
 */

public class UpdateIdCommand extends Commands {
    private DragonManager dragonManager;
    public UpdateIdCommand( DragonManager dragonManager ) {
        super( "update_id", "обновить значение элемента коллекции, id которого равен заданному" );
        this.dragonManager = dragonManager;
    }

    @Override
    public boolean execute( String[] argument ) {
        try{
            if ( argument[1].isEmpty() ) throw new IllegalArgumentException();

            long id = Long.valueOf( argument[1] );

            if ( !DragonManager.checkExistById(id) ) throw new IDIsNotFoundException();
            dragonManager.getCollection().remove( dragonManager.getById(id) );

            Dragon newDragon = Asker.buildDragon();
            newDragon.setId(id);
            dragonManager.getCollection().add( newDragon );

            Console.println( "Дракон успешно обновлен!" );
            return true;
        } catch ( IllegalArgumentException e ) {
            Console.printError("Неправильное количество аргументов!");
            Console.println( "Использование: " + getName() + "{id}" );
        } catch ( IDIsNotFoundException e ) {
            Console.printError( "Этот ID не существует!" );
        }
        return false;
    }
}
