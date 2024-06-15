package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
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
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        Long id = (Long) request.getParameter();
        try {
            if (!role.canUpdate()) throw new PermissionDeniedException();
            if (id == 0) throw new CommandSyntaxIsWrongException();
            if (id == -1) throw new NumberFormatException();
            Dragon oldDragon = collectionManager.getById(id);

            if (oldDragon == null) throw new IdNotFoundException();
            if (!oldDragon.getUser().toString().equals(request.getUser().toString()))
                throw new PermissionDeniedException();

            collectionManager.removeFromCollection(oldDragon);
            Dragon newDragon = (Dragon) request.getBonusParameter();
            newDragon.setId(id);
            collectionManager.addToCollection(newDragon, request.getUser());

            databaseCollectionManager.updateDragonById(id, newDragon);
            message = "That dragon was successfully updated";
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException e) {
            message = "Command syntax is not correct. Usage: \"" + getName() + " <id>\"";
            code = ProgramCode.ERROR;
        } catch (NumberFormatException e) {
            message = "The id must be long";
            code = ProgramCode.ERROR;
        } catch (IdNotFoundException e){
            message = "Number id [" + id + "] does not exist. Update unsuccessful dragons.";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e){
            message = "Not enough permissions to do this action.";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}