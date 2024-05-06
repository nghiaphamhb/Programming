package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.IdNotFoundException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * Delete the dragon that has the specified ID
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
            if (id == 0) throw new CommandSyntaxIsWrongException();
            if (id == -1) throw new NumberFormatException();
            Dragon dragon = dragonCollection.getById(id);

            if (dragon == null) return new Response("This id is not existed");

            dragonCollection.removeFromCollection( dragon );
            return new Response("The dragon with id \'" + id + "\' has been deleted from collection");
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " <id>\"");
        } catch (NumberFormatException e) {
            return new Response("The id must be long");
        }
    }

}
