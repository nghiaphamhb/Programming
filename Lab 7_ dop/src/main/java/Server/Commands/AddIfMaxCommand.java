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
 * Command to add a new dragon if his age is maximum in the collection
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("add_if_max", "add a new element to the collection, if its value is greater than the value of the largest element of this collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    @Override
    public Response execute (Request request, Role role) {
        if (collectionManager.getCollectionSize() == 0) return new Response("The collection is empty", ProgramCode.ERROR);
        String message = null;
        ProgramCode code = null;
        try {
            if (!role.canCreate()) throw new PermissionDeniedException();
            Dragon validatedDragon = (Dragon) request.getParameter();
            User user = request.getUser();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if ( validatedDragon.getAge() > getMaxAge() ) {
                if (!databaseCollectionManager.insertDragon(validatedDragon, user)) throw new FailureToActWithObjectException();
                collectionManager.addToCollection(validatedDragon, user);
                message = "The dragon has been successfully added! ";
                code = ProgramCode.OK;
            } else {
                message = "Could not add that dragon!";
                code = ProgramCode.ERROR;
            }
        } catch (CommandSyntaxIsWrongException e ) {
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

    /**
     * Search the oldest dragon in the collection
     * @return maximum age
     */
    private int getMaxAge() {
        int maxAge = collectionManager.getFirstDragon().getAge();
        for ( Dragon dragon : collectionManager.getCollection() ){
            if ( maxAge < dragon.getAge() )  maxAge = dragon.getAge();
        }
        return maxAge;
    }



}
