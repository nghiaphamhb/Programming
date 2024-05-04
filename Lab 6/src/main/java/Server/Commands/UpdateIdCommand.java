package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Common.Data.*;
import Server.Manager.DragonCollection;
import Client.Utility.DragonGenerator.Input;

/**
 * Команда для обновления информации у дракона, у которого указанный идентификатор
 */

public class UpdateIdCommand extends Commands {
    private DragonCollection dragonCollection;
    private Input input;
    public UpdateIdCommand(DragonCollection dragonCollection, Input input ) {
        super( "update_id", "update the value of a collection element whose id is equal to a given one" );
        this.dragonCollection = dragonCollection;
        this.input = input;
    }

    @Override
    public Response execute(Request request) {
        try{

            Long id = (Long) request.getArgumentCommand();
            Dragon dragon = dragonCollection.getById(id);

            if (dragon == null) return new Response("That id is not existed");
            dragonCollection.getCollection().remove( dragonCollection.getById(id) );

            Dragon newDragon = input.buildDragon();
            newDragon.setId(id);
            dragonCollection.getCollection().add( newDragon );

            return new Response("That dragon was successfully updated");
        } catch (Exception e ) {
            return new Response(e.toString());
        }
    }
}
