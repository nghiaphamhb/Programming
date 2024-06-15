package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CommandManager;
import Server.Utility.Role;

import java.util.stream.Collectors;

/**
 * Command to show the syntax of full commands
 */
public class HelpCommand extends AbstractCommand {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "display help on available commands");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute (Request request, Role role){
        String message = null;
        ProgramCode code = null;
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            message = commandManager.getCommands().stream().
                    filter(command -> !command.getDescription().equals("~hidden"))
                    .map(command -> String.format(" %-35s%-1s%n", command.getName(), command.getDescription() ))
                    .collect(Collectors.joining());
            code = ProgramCode.OK;
        } catch (CommandSyntaxIsWrongException e) {
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
