package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CommandManager;
import Server.Utility.Role.AbstractRole;

/**
 * Command to display the history of used commands (maximum only show the last 13 commands)
 */
public class HistoryCommand extends AbstractCommand {
    private final CommandManager commandManager;
    public HistoryCommand(CommandManager commandManager) {
        super("history", "print the last 13 commands (without their arguments)");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request, AbstractRole role) {
        try {
            if (!role.canRead()) throw new PermissionDeniedException();
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();

            String message = "<List of 13 last used commands>\n" +
                    commandManager.getCommandHistory().toString();
            return new Response(message);
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        } catch (PermissionDeniedException e) {
            return new Response("Not enough permissions to do this action", ProgramCode.ERROR);
        }
    }
}
