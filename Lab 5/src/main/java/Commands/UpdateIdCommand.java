package Commands;

import Data.*;
import Manager.*;
import utility.*;
import Exception.*;


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

            long idToUpdate = Long.valueOf( argument[1] );
            boolean isFind = false;

                if ( dragonManager.checkExistById(idToUpdate) ) {
                    isFind = true;
                    Dragon newDragon = Asker.buildDragon();
                    newDragon.setId( idToUpdate );
                    dragonManager.getCollection().remove( dragonManager.getById(idToUpdate) );
                    dragonManager.getCollection().add( newDragon );
                }


            if ( !isFind ) throw new IDIsNotFoundException();
            Console.println( "Дракон успешно обновлен!" );
        } catch ( IllegalArgumentException e ) {
            Console.println( "Использование: " + getName() + "{id}" );
        } catch ( IDIsNotFoundException e ) {
            Console.printError( "Этот ID не существует!" );
        }
        return true;
    }
}
