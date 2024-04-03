package Commands;

import Data.*;
import Manager.*;
import Utility.*;
import Exception.*;
import Utility.Input.Input;

/**
 * Команда для обновления информации у дракона, у которого указанный идентификатор
 */

public class UpdateIdCommand extends Commands {
    private DragonManager dragonManager;
    private Input input;
    public UpdateIdCommand( DragonManager dragonManager, Input input ) {
        super( "update_id", "обновить значение элемента коллекции, id которого равен заданному" );
        this.dragonManager = dragonManager;
        this.input = input;
    }

    @Override
    public boolean execute( String[] argument ) {
        try{
            if ( argument[1].isEmpty() ) throw new IllegalArgumentException();

            long id = Long.parseLong( argument[1] );

            if ( !DragonManager.checkExistById(id) ) throw new IDIsNotFoundException();
            dragonManager.getCollection().remove( dragonManager.getById(id) );

            Dragon newDragon = input.buildDragon();
            newDragon.setId(id);
            dragonManager.getCollection().add( newDragon );

            Display.println( "Дракон успешно обновлен!" );
            return true;
        } catch ( IllegalArgumentException e ) {
            Display.printError("Неправильное количество аргументов!");
            Display.println( "Использование: " + getName() + "{id}" );
        } catch ( IDIsNotFoundException e ) {
            Display.printError( "Этот ID не существует!" );
        }
        return false;
    }
}
