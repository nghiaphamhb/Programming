package Server.Commands;

import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import org.apache.commons.lang.NullArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * The command to read information from the dragon whose name is indicated
 */
public class FilterContainsNameCommand extends Commands {
    private final DragonCollection dragonCollection;
    public FilterContainsNameCommand( DragonCollection dragonCollection) {
        super("filter_contains_name", "display the elements, the value of the name field, which contains the given substring");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Собирать список драконов, имя которых указано
     * @param name указанное имя
     * @return список драконов
     */
    private List<Dragon> filterByName( String name ) {
        List<Dragon> dragonList = new ArrayList<>();
        for ( Dragon d : dragonCollection.getCollection() ) {
            if ( d.getName().equals(name) ) dragonList.add(d);
        }
        return dragonList;

    }

    @Override
    public Response execute (Request request) {
        try {
            String nameValidatedDragon = (String) request.getArgumentCommand();
            List<Dragon> dragonHasName = filterByName(nameValidatedDragon);

            if (nameValidatedDragon.isEmpty()) throw new Exception();

            if (dragonHasName.isEmpty()) {
                return new Response("The dragon with his name \'" + nameValidatedDragon + "\' is not existed");
            } else {
                StringBuilder message = new StringBuilder("The collection has " + dragonHasName.size() + " dragons with name \'" + nameValidatedDragon + "\n");
                for ( Dragon d : dragonHasName ){
                    message.append(d.toString()).append("\n");
                }
                return new Response(message.toString());
            }
        } catch (Exception exception) {
            return new Response("Syntax command is not correct. Usage \"" + getName() + " [name]\"");
        }
    }

}
