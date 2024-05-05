package Server.Commands;

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
            StringBuilder message = new StringBuilder();
            ArrayDeque<String> history = commandManager.getCommandHistory();
            for (String usedCommand : history){
                message.append(usedCommand).append("\n");
            }
            return new Response(message.toString());
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
