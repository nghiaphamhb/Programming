package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Client.Utility.Display;

import java.util.ArrayList;
import java.util.List;

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
            StringBuilder message = new StringBuilder("<List of dragons descending by speaking field values>");
            List<Dragon> dragonCanSpeak = new ArrayList<>();
            List<Dragon> dragonCannotSpeak = new ArrayList<>();

            for (Dragon dragon : dragonCollection.getCollection()) {
                if (dragon.getSpeaking()) dragonCanSpeak.add(dragon);
                else dragonCannotSpeak.add(dragon);
            }
            dragonCanSpeak.addAll(dragonCannotSpeak);

            for ( Dragon dragon : dragonCanSpeak ) {
                message.append("\n%s --> %s".formatted(dragon.getName(), dragon.getSpeaking()));
            }

            return new Response(message.toString());
        } catch (CommandSyntaxIsWrongException e) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }

}
