package Server.Commands;

import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Client.Utility.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда для показания списки драконов со своей способности говорить в порядке убывания
 */
public class PrintFieldDescendingSpeakingCommand extends Commands {
    private DragonCollection dragonCollection;
    public PrintFieldDescendingSpeakingCommand( DragonCollection dragonCollection) {
        super("print_field_descending_speaking", "display the speaking field values of all elements in descending order");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Показать имя и способность у дракона
     * @param listDragon список драконов
     */
    private void print_Name_Speaking ( List<Dragon> listDragon) {
        for ( Dragon dragon : listDragon ) {
            Display.println( dragon.getName() + " -> " + dragon.getSpeaking() );
        }
    }

    @Override
    public Response execute(Request request) {
        try{
            StringBuilder messagge = new StringBuilder();
            List<Dragon> dragonCanSpeak = new ArrayList<>();
            List<Dragon> dragonCannotSpeak = new ArrayList<>();

            for (Dragon dragon : dragonCollection.getCollection()) {
                if (dragon.getSpeaking()) dragonCanSpeak.add(dragon);
                else dragonCannotSpeak.add(dragon);
            }
            dragonCanSpeak.addAll(dragonCannotSpeak);

            for ( Dragon dragon : dragonCanSpeak ) {
                messagge.append("%s --> %s \n".formatted(dragon.getName(), dragon.getSpeaking()));
            }

            return new Response(messagge.toString());
        } catch (Exception e) {
            return new Response(e.toString());
        }
    }

}
