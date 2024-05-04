package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

import java.time.LocalDateTime;

/**
 * The command for reading information from this collection
 */
public class InfoCommand extends Commands {
    private final DragonCollection dragonCollection;
    public InfoCommand(DragonCollection dragonCollection) {
        super("info", "print information about the collection to standard output (type, initialization date, number of elements, etc.)");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public Response execute(Request request){
        try {
            LocalDateTime createTime = dragonCollection.getCreateTime();
            String creatTimeString = (createTime == null) ? "initialization has not yet occurred in this session" :
                    createTime.toLocalDate().toString() + " " + createTime.toLocalTime().toString();

            LocalDateTime saveTime = dragonCollection.getSaveTime();
            String saveTimeString = (saveTime == null) ? "there has not been a save in this session yet" :
                    saveTime.toLocalDate().toString() + " " + saveTime.toLocalTime().toString();

            String message = "Information about the collection:\n" +
                    " Type: " + dragonCollection.getCollectionType() + "\n" +
                    " Number of elements: " + dragonCollection.getCollectionSize() + "\n" +
                    " Date of last save: " + saveTimeString + "\n" +
                    " Date of last initialization: " + creatTimeString;
            return new Response(message);
        } catch (Exception exception) {
            return new Response(exception.toString());
        }
    }
}
