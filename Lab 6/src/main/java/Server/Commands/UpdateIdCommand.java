package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.IdNotFoundException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Data.*;
import Server.Manager.DragonCollection;
import Client.Utility.DragonGenerator.Input;

/**
 * The command to update information from the dragon that has the specified ID
 */

public class UpdateIdCommand extends Commands {
    private DragonCollection dragonCollection;

    public UpdateIdCommand(DragonCollection dragonCollection) {
        super("update_id", "update the value of a collection element whose id is equal to a given one");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public Response execute(Request request) {
        try {
            Long id = (Long) request.getArgumentCommand();
            if (id == 0) throw new CommandSyntaxIsWrongException();
            if (id == -1) throw new NumberFormatException();
            Dragon dragon = dragonCollection.getById(id);

            if (dragon == null) return new Response("Id [" + id + "] does not exist. Update unsuccessful dragons.");
            dragonCollection.getCollection().remove(dragon);

            Dragon newDragon = request.getUpdatedDragon();
            newDragon.setId(id);
            dragonCollection.getCollection().add(newDragon);

            return new Response("That dragon was successfully updated");
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " <id>\"");
        } catch (NumberFormatException e) {
            return new Response("The id must be long");
        }
    }
}