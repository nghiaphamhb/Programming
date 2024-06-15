package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Utility.Role;

import java.io.FileNotFoundException;

/**
 * Command to execute scripts
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file");
    }

    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try {
            if (!role.canExecute()) throw new PermissionDeniedException();
            String nameScript = (String) request.getParameter();
            if (nameScript.isEmpty()) throw new CommandSyntaxIsWrongException();
            if (nameScript.equals("cannot find")) throw new FileNotFoundException();
            message = "Processed script " + nameScript + ".";
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException exception) {
            message = "Command syntax is not correct. Usage: \"" + getName() + " [fileName]\"";
            code = ProgramCode.ERROR;
        } catch (FileNotFoundException e) {
            message = "That script is not exist.";
            code = ProgramCode.ERROR;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
