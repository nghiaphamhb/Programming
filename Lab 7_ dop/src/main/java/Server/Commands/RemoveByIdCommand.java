package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CollectionIsEmptyException;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.IdNotFoundException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Utility.Role;

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
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try{
            if (!role.canDelete()) throw new PermissionDeniedException();
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
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException e) {
            message = "Command syntax is not correct. Usage: \"" + getName() + " <id>\"";
            code = ProgramCode.ERROR;
        } catch (NumberFormatException e) {
            message = "The id must be long";
            code = ProgramCode.ERROR;
        } catch (CollectionIsEmptyException e){
            message = "The collection is empty.";
            code = ProgramCode.ERROR;
        } catch (IdNotFoundException e){
            message = "This id is not existed";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }


}
