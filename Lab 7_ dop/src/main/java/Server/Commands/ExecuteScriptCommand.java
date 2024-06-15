package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Utility.Roles.Role;

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
        try {
            if (!role.canExecute()) throw new PermissionDeniedException();
            String nameScript = (String) request.getParameter();
            if (nameScript.isEmpty()) throw new CommandSyntaxIsWrongException();
            if (nameScript.equals("cannot find")) throw new FileNotFoundException();
            return new Response("Processed script " + nameScript + ".");
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " [fileName]\"", ProgramCode.ERROR);
        } catch (FileNotFoundException e) {
            return new Response("That script is not exist.", ProgramCode.ERROR);
        } catch (PermissionDeniedException e) {
            return new Response("Not enough permissions to do this action", ProgramCode.ERROR);
        }
    }
}
