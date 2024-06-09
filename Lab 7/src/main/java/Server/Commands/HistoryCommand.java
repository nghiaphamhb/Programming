package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;

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
    public Response execute(Request request) {
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();

            String message = "<List of 13 last used commands>\n" +
                    commandManager.getCommandHistory().toString();
            return new Response(message);
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
