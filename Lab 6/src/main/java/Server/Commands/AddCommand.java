package Server.Commands;

import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Client.Utility.DragonGenerator.Input;

/**
 * The command to add a dragon to the collection
 */
public class AddCommand extends Commands {
    private DragonCollection dragonCollection;
    private Input input;
    public AddCommand(DragonCollection dragonCollection, Input input ) {
        super("add", "add a new element to the collection");
        this.dragonCollection = dragonCollection;
        this.input = input;
    }


    @Override
    public Response execute(Request request) {
        try {
            Dragon validatedDragon = (Dragon) request.getArgumentCommand();
            dragonCollection.addToCollection(validatedDragon);
            return new Response("The dragon has been successfully added!");
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
