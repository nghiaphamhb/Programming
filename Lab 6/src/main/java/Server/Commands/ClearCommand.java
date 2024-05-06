package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * The command for cleaning this collection
 */
public class ClearCommand extends Commands {
    private final DragonCollection dragonCollection;
    public ClearCommand( DragonCollection dragonCollection) {
        super("clear", "clear collection");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            dragonCollection.clearCollection();
            return new Response("Collection cleared!");
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
