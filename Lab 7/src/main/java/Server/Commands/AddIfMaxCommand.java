package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToCreateObjectException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;

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
    public Response execute (Request request) {
        if (collectionManager.getCollectionSize() == 0) return new Response("The collection is empty");
        try {
            Dragon validatedDragon = (Dragon) request.getParameter();
            User user = request.getUser();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if ( validatedDragon.getAge() > getMaxAge() ) {
                if (!databaseCollectionManager.insertDragon(validatedDragon, user)) throw new FailureToCreateObjectException();
                collectionManager.addToCollection(validatedDragon, user);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch (CommandSyntaxIsWrongException e ) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        } catch (FailureToCreateObjectException e){
            return new Response("Failure to insert dragon into collection.", ProgramCode.ERROR);
        }
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
