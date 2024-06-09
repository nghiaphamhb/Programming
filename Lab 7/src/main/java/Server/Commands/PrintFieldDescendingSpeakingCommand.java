package Server.Commands;

import Common.Data.Dragon.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The command to read lists of dragons with their ability to speak in descending order
 */
public class PrintFieldDescendingSpeakingCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public PrintFieldDescendingSpeakingCommand( CollectionManager collectionManager) {
        super("print_field_descending_speaking", "display the speaking field values of all elements in descending order");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try{
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            String message = "<List of dragons descending by speaking field values> \n" +
                collectionManager.getCollection().stream()
                .sorted(Comparator.comparing(Dragon::getSpeaking).reversed())
                                .map(dragon -> "Can \"" + dragon.getName() + "\" speak? => " + ( dragon.getSpeaking()  ? "Yes" : "No"))
                        .collect(Collectors.joining("\n"));

        return new Response(message);
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}

