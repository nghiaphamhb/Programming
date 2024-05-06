package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * The command to add a dragon to the collection
 */
public class AddCommand extends Commands {
    private DragonCollection dragonCollection;

    public AddCommand(DragonCollection dragonCollection) {
        super("add", "add a new element to the collection");
        this.dragonCollection = dragonCollection;
    }


    @Override
    public Response execute(Request request) {
        try {
            Dragon validatedDragon = (Dragon) request.getArgumentCommand();
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            dragonCollection.addToCollection(validatedDragon);
            return new Response("The dragon has been successfully added!");
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
