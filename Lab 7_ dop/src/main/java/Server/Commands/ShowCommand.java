package Server.Commands;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;
import Server.Utility.Role;

/**
 * Show all dragon in the collection
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "print to standard output all the elements of the collection in string representation");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try {
            if (!role.canRead()) throw new PermissionDeniedException();
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            message = collectionManager.getCollection().toString();
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
