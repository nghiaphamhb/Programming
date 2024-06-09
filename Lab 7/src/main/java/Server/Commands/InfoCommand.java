package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Memory.CollectionManager;

import java.time.LocalDateTime;

/**
 * The command for reading information from this collection
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "print information about the collection to standard output (type, initialization date, number of elements, etc.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        try {
            if (request.getParameter() != null) throw new CommandSyntaxIsWrongException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "initialization has not yet occurred in this session" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime saveTime = collectionManager.getSaveTime();
            String saveTimeString = (saveTime == null) ? "there has not been a save in this session yet" :
                    saveTime.toLocalDate().toString() + " " + saveTime.toLocalTime().toString();

            String message = "Information about the collection:\n" +
                    " Type: " + collectionManager.getCollectionType() + "\n" +
                    " Number of elements: " + collectionManager.getCollectionSize() + "\n" +
                    " Date of last save: " + saveTimeString + "\n" +
                    " Date of last initialization: " + lastInitTime;
            return new Response(message);
        } catch (CommandSyntaxIsWrongException exception) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
