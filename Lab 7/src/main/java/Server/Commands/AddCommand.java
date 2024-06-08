package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToCreateObjectException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.CollectionManager;
import Server.Manager.DatabaseCollectionManager;

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
    public Response execute(Request request) {
        try {
            Dragon validatedDragon = (Dragon) request.getParameter();
            User user = request.getUser();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if (!databaseCollectionManager.insertDragon(validatedDragon, user)) throw new FailureToCreateObjectException();
            collectionManager.addToCollection(validatedDragon, user);
            return new Response("The dragon has been successfully added!");
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"", ProgramCode.ERROR);
        } catch (FailureToCreateObjectException e){
            return new Response("Failure to insert dragon into collection.", ProgramCode.ERROR);
        }
    }
}
