package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.IdNotFoundException;
import Common.Exception.PermissionDeniedException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;

/**
 * The command to update information from the dragon that has the specified ID
 */

public class UpdateIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public UpdateIdCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("update_id", "update the value of a collection element whose id is equal to a given one");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    @Override
    public Response execute(Request request) {
        String message = "";
        Long id = (Long) request.getParameter();
        try {
            if (id == 0) throw new CommandSyntaxIsWrongException();
            if (id == -1) throw new NumberFormatException();
            Dragon oldDragon = collectionManager.getById(id);
            if (oldDragon == null) throw new IdNotFoundException();
            if (!oldDragon.getUser().equals(request.getUser())) throw new PermissionDeniedException();
            collectionManager.getCollection().remove(oldDragon);
            Dragon newDragon = request.getUpdatedDragon();
            newDragon.setId(id);
            collectionManager.getCollection().add(newDragon);

            databaseCollectionManager.updateDragonById(id, newDragon);
            return new Response("That dragon was successfully updated");
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " <id>\"");
        } catch (NumberFormatException e) {
            return new Response("The id must be long");
        } catch (IdNotFoundException e){
            message = "Number id [" + id + "] does not exist. Update unsuccessful dragons.";
        } catch (PermissionDeniedException e){
            message = "Not enough permissions to update this element from the collection.";
        }
        return new Response(message);
    }
}