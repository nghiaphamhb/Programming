package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Client.Utility.Display;
import Server.Manager.DragonCollection;

/**
 * Команда для уборки данной коллекции
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
            Display.println("Collection cleared!");
            return new Response(null);
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
