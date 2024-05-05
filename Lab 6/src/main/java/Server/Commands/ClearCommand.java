package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Client.Utility.Display;
import Server.Manager.DragonCollection;

/**
 * The team for cleaning this collection
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
            dragonCollection.clearCollection();
            return new Response("Collection cleared!");
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
