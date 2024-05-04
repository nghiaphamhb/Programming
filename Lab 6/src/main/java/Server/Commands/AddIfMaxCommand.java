package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Common.Data.*;
import Client.Utility.DragonGenerator.Input;

/**
 * Команда для добавления дракона в коллекцию при тем, что этот дракон считается самым старым в коллекции
 */
public class AddIfMaxCommand extends Commands {
    private DragonCollection dragonCollection;
    private Input input;

    public AddIfMaxCommand(DragonCollection dragonCollection, Input input) {
        super("add_if_max", "add a new element to the collection, if its value is greater than the value of the largest element of this collection");
        this.dragonCollection = dragonCollection;
        this.input = input;
    }

    /**
     * Найти наибольший возраст у дракона в коллекции
     * @return наибольший возраст
     */
    private int getMaxAge() {
        int maxAge = dragonCollection.getFirstDragon().getAge();
        for ( Dragon dragon : dragonCollection.getCollection() ){
            if ( maxAge < dragon.getAge() )  maxAge = dragon.getAge();
        }
        return maxAge;
    }


    @Override
    public Response execute (Request request) {
        if (dragonCollection.getCollectionSize() == 0) return new Response("The collection is empty");
        try {
            Dragon validatedDragon = (Dragon) request.getArgumentCommand();

            if ( validatedDragon.getAge() > getMaxAge() ) {
                dragonCollection.addToCollection(validatedDragon);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch ( Exception e ) {
            return new Response(e.toString());
        }
    }





}
