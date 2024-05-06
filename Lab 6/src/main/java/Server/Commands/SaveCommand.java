package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

/**
 * Save collection into json file
 */
public class SaveCommand extends Commands {
    private final DragonCollection dragonCollection;
    public SaveCommand( DragonCollection dragonCollection) {
        super("save", "save the collection to a file");
        this.dragonCollection = dragonCollection;
    }


    @Override
    public Response execute(Request request) {
        try {
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            dragonCollection.saveCollection();
            return new Response("The collection is saved");
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
