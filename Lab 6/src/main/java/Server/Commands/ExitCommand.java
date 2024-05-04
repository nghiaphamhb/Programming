package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;

/**
 * Команда для окончания программы
 */
public class ExitCommand extends Commands {
    public ExitCommand() {
        super("exit", "end the program (without saving to a file)");
    }

    @Override
    public Response execute(Request request) {
        try {
            return new Response("The program was stopped");
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
