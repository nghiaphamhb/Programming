package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Utility.Roles.Role;

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
        try{
            if (!role.canRead()) throw new PermissionDeniedException();
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();

            String message = "<List of dragons descending by age> \n" +
                    collectionManager.getCollection().stream()
                            .sorted(Comparator.comparingInt(Dragon::getAge).reversed())
                            .map(Dragon::toString)
                            .collect(Collectors.joining());

            return new Response(message);
        } catch (CommandSyntaxIsWrongException e){
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        } catch (PermissionDeniedException e) {
            return new Response("Not enough permissions to do this action", ProgramCode.ERROR);
        }
    }
}
