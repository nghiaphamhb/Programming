package Server.Commands;

import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;

import java.util.ArrayList;
import java.util.List;

/**
 *The team for the reading lists the dragons with their information in descending order of age
 */
public class PrintDescendingCommand extends Commands {
    private DragonCollection dragonCollection;

    public PrintDescendingCommand(DragonCollection dragonCollection) {
        super("print_descending", "display collection elements in descending order of age");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Создать список драконов в порядке убывания возраста
     * @param dragonCollection данная коллекция
     * @return список драконов
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
            StringBuilder message = new StringBuilder("List of dragons descending by age: \n");
            List<Dragon> listByAge = sortDragonsByAge(dragonCollection);
            for (Dragon dragon : listByAge){
                message.append(dragon).append("\n");
            }
            return new Response(message.toString());
        } catch (Exception e){
            return new Response(e.toString());
        }
    }
}
