package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.CollectionManager;
import Server.Manager.DatabaseCollectionManager;

/**
 * The command for cleaning this collection
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final DatabaseCollectionManager databaseCollectionManager;

    public ClearCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    @Override
    public Response execute(Request request) {
        String message = "";
        ProgramCode code = null;
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            User user = request.getUser();
            for (Dragon dragon : collectionManager.getCollection()){
                if (dragon.getUser().equals(user)){
                    databaseCollectionManager.deleteDragonById(dragon.getId());
                    collectionManager.removeFromCollection(dragon);
                }
            }
            if (collectionManager.getCollectionSize() != 0) throw new PermissionDeniedException();
//            databaseCollectionManager.clearCollection();
//            collectionManager.clearCollection();
            return new Response("Collection cleared!");
        } catch (CommandSyntaxIsWrongException exception) {
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e){
            message = "Not enough permissions to clear all elements in the collection. \n Successfully deleted owned elements.";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
