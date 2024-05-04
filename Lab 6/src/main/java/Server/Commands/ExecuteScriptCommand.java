package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;

/**
 * Команда для выполнения скрипты
 */
public class ExecuteScriptCommand extends Commands {
    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file");
    }

    @Override
    public Response execute(Request request) {
        try {
            return new Response("Processing script " + request.getArgumentCommand() + "...");
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
