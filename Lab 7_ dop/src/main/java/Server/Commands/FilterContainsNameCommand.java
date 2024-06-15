package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Utility.Role;

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
    public Response execute (Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try {
            if (!role.canRead()) throw new PermissionDeniedException();
            String nameValidatedDragon = (String) request.getParameter();
            if (nameValidatedDragon.isEmpty()) throw new CommandSyntaxIsWrongException();

           String filteredDragon = collectionManager.getCollection().stream()
                    .filter(dragon -> dragon.getName().equals(nameValidatedDragon))
                    .map(Dragon::toString)
                    .collect(Collectors.joining("\n"));

           if(filteredDragon.isEmpty()) return new Response("The dragon with his name \'" + nameValidatedDragon + "\' is not existed",
                    ProgramCode.ERROR);

           message = "<The list of dragons with name \'" + nameValidatedDragon + "\'>\n"
                    + filteredDragon;
           code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException exception) {
            message = "Syntax command is not correct. Usage \"" + getName() + " [name]\"";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }

}
