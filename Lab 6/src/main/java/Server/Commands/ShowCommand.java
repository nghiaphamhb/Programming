package Server.Commands;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * Показать информации у всех драконов в данной коллекции
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
            return new Response(dragonCollection.getCollection().toString());
        } catch (Exception e) {
            return new Response(e.toString());
        }
    }
}
