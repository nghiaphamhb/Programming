package Server.Commands;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * Show all dragon in the collection
 */
public class ShowCommand extends Commands {
    private DragonCollection dragonCollection;
    public ShowCommand(DragonCollection dragonCollection) {
        super("show", "print to standard output all the elements of the collection in string representation");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            return new Response(dragonCollection.getCollection().toString());
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
