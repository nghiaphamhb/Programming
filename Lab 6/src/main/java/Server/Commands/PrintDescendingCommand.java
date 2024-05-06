package Server.Commands;

import Common.Data.Dragon;
import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

import java.util.ArrayList;
import java.util.List;

/**
 *The command for the reading lists the dragons with their information in descending order of age
 */
public class PrintDescendingCommand extends Commands {
    private DragonCollection dragonCollection;

    public PrintDescendingCommand(DragonCollection dragonCollection) {
        super("print_descending", "display collection elements in descending order of age");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Make a list of dragons in the order of decreasing age
     * @param dragonCollection dragon collection
     * @return list of dragons
     */
    private List<Dragon> sortDragonsByAge(DragonCollection dragonCollection) {
        List<Dragon> dragonList = new ArrayList<>(dragonCollection.getCollection());
        for (int i = dragonList.size() - 1; i > 0; i--){    //algorithm buble sort
            for (int j = 0; j < i; j++){
                if (dragonList.get(j).getAge() < dragonList.get(j + 1).getAge()){
                    Dragon temp = dragonList.get(j);
                    dragonList.set(j, dragonList.get(j+1));
                    dragonList.set(j+1, temp);
                }
            }
        }
        return dragonList;
    }

    @Override
    public Response execute(Request request) {
        try{
            if (request.getArgumentCommand() != null) throw new CommandSyntaxIsWrongException();
            StringBuilder message = new StringBuilder("<List of dragons descending by age> \n");
            List<Dragon> listByAge = sortDragonsByAge(dragonCollection);
            for (Dragon dragon : listByAge){
                message.append(dragon).append("\n");
            }
            return new Response(message.toString());
        } catch (CommandSyntaxIsWrongException e){
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }
}
