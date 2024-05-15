package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *The command for the reading lists the dragons with their information in descending order of age
 */
public class PrintDescendingCommand extends Commands {
    private DragonCollection dragonCollection;

    public PrintDescendingCommand(DragonCollection dragonCollection) {
        super("print_descending", "display collection elements in descending order of age");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public Response execute(Request request) {
        try{
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();

            String message = "<List of dragons descending by age> \n" +
                    dragonCollection.getCollection().stream()
                            .sorted(Comparator.comparingInt(Dragon::getAge).reversed())
                            .map(Dragon::toString)
                            .collect(Collectors.joining());

            return new Response(message);
        } catch (CommandSyntaxIsWrongException e){
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
