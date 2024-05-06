package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;

import java.util.ArrayDeque;

/**
 * Command to display the history of used commands (maximum only show the last 13 commands)
 */
public class HistoryCommand extends Commands {
    private final CommandManager commandManager;
    public HistoryCommand(CommandManager commandManager) {
        super("history", "print the last 13 commands (without their arguments)");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            StringBuilder message = new StringBuilder("<List of used commands>\n");
            ArrayDeque<String> history = commandManager.getCommandHistory();
            for (String usedCommand : history){
                message.append(usedCommand).append("\n");
            }
            return new Response(message.toString());
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
