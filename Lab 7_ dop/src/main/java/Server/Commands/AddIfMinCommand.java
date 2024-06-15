package Server.Commands;

import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToActWithObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Common.Data.Dragon.Dragon;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Utility.Role;

/**
 * Command to add a new dragon if his age is maximum in the collection
 */
public class AddIfMinCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public AddIfMinCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("add_if_min", "add a new element to the collection, if its value is less than the smallest element of this collection");
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
            if ( validatedDragon.getAge() < getMinAge() ) {
                validatedDragon = databaseCollectionManager.insertDragon(validatedDragon, user);
                if (validatedDragon == null ) throw new FailureToActWithObjectException();
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
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }

    /**
     * Search the youngest dragon in the collection
     * @return minimum age
     */
    private int getMinAge (){
        int minAge = collectionManager.getFirstDragon().getAge();
        for ( Dragon d : collectionManager.getCollection() ) {
            if ( minAge < d.getId() ) minAge = d.getAge();
        }
        return minAge;
    }


}
