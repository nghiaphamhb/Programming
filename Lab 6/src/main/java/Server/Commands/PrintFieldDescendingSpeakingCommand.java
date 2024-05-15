package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Client.Utility.Display;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The command to read lists of dragons with their ability to speak in descending order
 */
public class PrintFieldDescendingSpeakingCommand extends Commands {
    private DragonCollection dragonCollection;
    public PrintFieldDescendingSpeakingCommand( DragonCollection dragonCollection) {
        super("print_field_descending_speaking", "display the speaking field values of all elements in descending order");
        this.dragonCollection = dragonCollection;
    }


    @Override
    public Response execute(Request request) {
        try{
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            String message = "<List of dragons descending by speaking field values> \n" +
                dragonCollection.getCollection().stream()
                .sorted(Comparator.comparing(Dragon::getSpeaking).reversed())
                                .map(dragon -> "Can \"" + dragon.getName() + "\" speak? => " + ( dragon.getSpeaking()  ? "Yes" : "No"))
                        .collect(Collectors.joining("\n"));

        return new Response(message);
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}

