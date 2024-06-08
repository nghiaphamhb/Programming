package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CollectionManager;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *The command for the reading lists the dragons with their information in descending order of age
 */
public class PrintDescendingCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "display collection elements in descending order of age");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try{
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();

            String message = "<List of dragons descending by age> \n" +
                    collectionManager.getCollection().stream()
                            .sorted(Comparator.comparingInt(Dragon::getAge).reversed())
                            .map(Dragon::toString)
                            .collect(Collectors.joining());

            return new Response(message);
        } catch (CommandSyntaxIsWrongException e){
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
