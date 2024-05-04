package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;

/**
 * Команда для показания списка данных команд со своим описанием
 */
public class HelpCommand extends Commands {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "display help on available commands");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute (Request request){
        try {
            StringBuilder message = new StringBuilder();
            for ( Commands c : commandManager.getCommands() ){
                message.append(" %-35s%-1s%n".formatted(c.getName(), c.getDescription()));
            }
            return new Response(message.toString());
        } catch (Exception e) {
            return new Response(e.toString());
        }
    }
}
