package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;

import java.io.FileNotFoundException;

/**
 * Command to execute scripts
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file");
    }

    @Override
    public Response execute(Request request) {
        try {
            String nameScript = (String) request.getParameter();
            if (nameScript.isEmpty()) throw new CommandSyntaxIsWrongException();
            if (nameScript.equals("-1")) throw new FileNotFoundException();
            return new Response("Processed script " + nameScript + ".");
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " [fileName]\"");
        } catch (FileNotFoundException e) {
            return new Response("That script is not exist.");
        }
    }
}
