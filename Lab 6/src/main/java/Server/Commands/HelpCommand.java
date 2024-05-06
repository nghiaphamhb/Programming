package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;

/**
 * Command to show the syntax of full commands
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
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            StringBuilder message = new StringBuilder();
            for ( Commands c : commandManager.getCommands() ){
                if (c.getName().equals("NoSuchCommand")) continue;
                message.append(" %-35s%-1s%n".formatted(c.getName(), c.getDescription()));
            }
            return new Response(message.toString());
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
