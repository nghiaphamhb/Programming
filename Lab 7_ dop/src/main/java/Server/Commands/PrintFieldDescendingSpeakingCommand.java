package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Utility.Role;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The command to read lists of dragons with their ability to speak in descending order
 */
public class PrintFieldDescendingSpeakingCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public PrintFieldDescendingSpeakingCommand( CollectionManager collectionManager) {
        super("print_field_descending_speaking", "display the speaking field values of all elements in descending order");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try{
            if (!role.canRead()) throw new PermissionDeniedException();
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            message = "<List of dragons descending by speaking field values> \n" +
                collectionManager.getCollection().stream()
                .sorted(Comparator.comparing(Dragon::getSpeaking).reversed())
                                .map(dragon -> "Can \"" + dragon.getName() + "\" speak? => " + ( dragon.getSpeaking()  ? "Yes" : "No"))
                        .collect(Collectors.joining("\n"));
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException e) {
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}

