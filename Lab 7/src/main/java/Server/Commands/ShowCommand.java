package Server.Commands;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CollectionManager;

/**
 * Show all dragon in the collection
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "print to standard output all the elements of the collection in string representation");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            return new Response(collectionManager.getCollection().toString());
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
