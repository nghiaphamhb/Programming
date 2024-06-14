package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CollectionIsEmptyException;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.IdNotFoundException;
import Common.Exception.PermissionDeniedException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;

/**
 * Delete the dragon that has the specified ID
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_by_id", "remove an element from a collection by its id");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }


    @Override
    public Response execute(Request request) {
        String message = "";
        try{
            Long id = (Long) request.getParameter();
            User user = request.getUser();
            if (id == 0) throw new CommandSyntaxIsWrongException();
            if (collectionManager.getCollectionSize() == 0) throw new CollectionIsEmptyException();
            if (id == -1) throw new NumberFormatException();
            Dragon dragon = collectionManager.getById(id);
            if (dragon == null) throw new IdNotFoundException();
            if (!dragon.getUser().equals(user)) throw new PermissionDeniedException();
            databaseCollectionManager.deleteDragonById(id);
            collectionManager.removeFromCollection( dragon );
            message = "The dragon with id \'" + id + "\' has been deleted from collection";
        } catch (CommandSyntaxIsWrongException e) {
            message = "Command syntax is not correct. Usage: \"" + getName() + " <id>\"";
        } catch (NumberFormatException e) {
            message = "The id must be long";
        } catch (CollectionIsEmptyException e){
            message = "The collection is empty.";
        } catch (IdNotFoundException e){
            message = "This id is not existed";
        } catch (PermissionDeniedException e){
            message = "Not enough permissions to remove this element from the collection.";
        }
        return new Response(message);
    }


}
