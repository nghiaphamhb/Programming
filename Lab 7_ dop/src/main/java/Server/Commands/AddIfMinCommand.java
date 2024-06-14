package Server.Commands;

import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToCreateObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Common.Data.Dragon.Dragon;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Utility.Role.AbstractRole;

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
    public Response execute (Request request, AbstractRole role) {
        if (collectionManager.getCollectionSize() == 0) return new Response("The collection is empty");
        try {
            if (!role.canCreate()) throw new PermissionDeniedException();
            Dragon validatedDragon = (Dragon) request.getParameter();
            User user = request.getUser();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if ( validatedDragon.getAge() < getMinAge() ) {
                if (!databaseCollectionManager.insertDragon(validatedDragon, user)) throw new FailureToCreateObjectException();
                collectionManager.addToCollection(validatedDragon, user);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch (CommandSyntaxIsWrongException e ) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        } catch (PermissionDeniedException e) {
            return new Response("Not enough permissions to do this action", ProgramCode.ERROR);
        }
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
