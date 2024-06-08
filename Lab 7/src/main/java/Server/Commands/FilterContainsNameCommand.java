package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CollectionManager;

import java.util.stream.Collectors;

/**
 * The command to read information from the dragon whose name is indicated
 */
public class FilterContainsNameCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    public FilterContainsNameCommand( CollectionManager collectionManager) {
        super("filter_contains_name", "display the elements, the value of the name field, which contains the given substring");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute (Request request) {
        try {
            String nameValidatedDragon = (String) request.getParameter();
            if (nameValidatedDragon.isEmpty()) throw new CommandSyntaxIsWrongException();

           String  filteredDragon = collectionManager.getCollection().stream()
                    .filter(dragon -> dragon.getName().equals(nameValidatedDragon))
                    .map(Dragon::toString)
                    .collect(Collectors.joining("\n"));

            if(filteredDragon.isEmpty()) return new Response("The dragon with his name \'" + nameValidatedDragon + "\' is not existed");
            String message = "<The list of dragons with name \'" + nameValidatedDragon + "\'>\n"
                    + filteredDragon;

            return new Response(message);

        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage \"" + getName() + " [name]\"");
        }
    }

}
