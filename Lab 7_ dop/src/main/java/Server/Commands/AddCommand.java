package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToActWithObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Utility.Role;

/**
 * The command to add a dragon to the collection
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public AddCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("add", "add a new element to the collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }


    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try {
            if (!role.canCreate()) throw new PermissionDeniedException();
            Dragon validatedDragon = (Dragon) request.getParameter();
            User user = request.getUser();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if (!databaseCollectionManager.insertDragon(validatedDragon, user)) throw new FailureToActWithObjectException();
            collectionManager.addToCollection(validatedDragon, user);
            message = "The dragon has been successfully added!";
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException exception) {
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        } catch (FailureToActWithObjectException e){
            message = "Failure to insert dragon into collection.";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
