package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Utility.Role;

/**
 * The command to end the program
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "end the program");
    }


    @Override
    public Response execute(Request request, Role role) {
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            return new Response("The program was stopped", ProgramCode.CLIENT_EXIT);
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"", ProgramCode.ERROR);
        }
    }
}
