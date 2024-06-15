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
 *The command for the reading lists the dragons with their information in descending order of age
 */
public class PrintDescendingCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "display collection elements in descending order of age");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try{
            if (!role.canRead()) throw new PermissionDeniedException();
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();

            message = "<List of dragons descending by age> \n" +
                    collectionManager.getCollection().stream()
                            .sorted(Comparator.comparingInt(Dragon::getAge).reversed())
                            .map(Dragon::toString)
                            .collect(Collectors.joining());

            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException e){
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
