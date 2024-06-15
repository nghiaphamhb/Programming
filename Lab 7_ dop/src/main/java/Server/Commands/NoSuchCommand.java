package Server.Commands;

import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Utility.Role;

/**
 * Command is not available
 */
public class NoSuchCommand extends AbstractCommand {

    public NoSuchCommand() {
        super("NoSuchCommand", "~hidden");
    }

    @Override
    public Response execute (Request request, Role role){
        String message = "That command is not available. Please enter \"help\" for helping";
        return new Response(message, ProgramCode.ERROR);
    }
}
