package Server.Commands;

import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * Удалить дракона, у которого указанный идентификатор
 */
public class RemoveByIdCommand extends Commands {
    private DragonCollection dragonCollection;
    public RemoveByIdCommand(DragonCollection dragonCollection) {
        super("remove_by_id", "remove an element from a collection by its id");
        this.dragonCollection = dragonCollection;
    }


    @Override
    public Response execute(Request request) {
        try{
            Long id = (Long) request.getArgumentCommand();
            Dragon dragon = dragonCollection.getById(id);

            if (dragon == null) return new Response("This id is not existed");

            dragonCollection.removeFromCollection( dragon );
            return new Response("The dragon with id " + id + "has been added into collection");
        } catch (Exception e){
            return new Response(e.toString());
        }
    }

}
