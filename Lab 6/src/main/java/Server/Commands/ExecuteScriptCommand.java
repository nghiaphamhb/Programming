package Server.Commands;

import Common.Exception.CommandSyntaxIsIncorrectException;
import Common.Network.Request;
import Common.Network.Response;

import java.io.FileNotFoundException;

/**
 * Command to execute scripts
 */
public class ExecuteScriptCommand extends Commands {
    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file");
    }

    @Override
    public Response execute(Request request) {
        try {
            String nameScript = (String) request.getArgumentCommand();
            if (nameScript.isEmpty()) throw new CommandSyntaxIsIncorrectException();
            if (nameScript.equals("-1")) throw new FileNotFoundException();
            return new Response("Processing script " + nameScript + "...");
        } catch (CommandSyntaxIsIncorrectException exception) {
            return new Response("Command syntax is not correct. Usage: \"" + getName() + " [fileName]\"");
        } catch (FileNotFoundException e) {
            return new Response("That script is not exist.");
        }
    }
}
