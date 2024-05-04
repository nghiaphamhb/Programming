package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Common.Data.Dragon;
import Server.Manager.DragonCollection;
import Client.Utility.DragonGenerator.Input;

/**
 * Команда для добавления дракона в коллекцию при тем, что этот дракон считается самым молодым в коллекции
 */
public class AddIfMinCommand extends Commands {
    private DragonCollection dragonCollection;
    private Input input;

    public AddIfMinCommand(DragonCollection dragonCollection, Input input) {
        super("add_if_min", "add a new element to the collection, if its value is less than the smallest element of this collection");
        this.dragonCollection = dragonCollection;
        this.input = input;
    }

    /**
     * Найти наименьший возраст у дракона в коллекции
     * @return наименьший возраст
     */
    private int getMinAge (){
        int minAge = dragonCollection.getFirstDragon().getAge();
        for ( Dragon d : dragonCollection.getCollection() ) {
            if ( minAge < d.getId() ) minAge = d.getAge();
        }
        return minAge;
    }

    @Override
    public Response execute (Request request) {
        if (dragonCollection.getCollectionSize() == 0) return new Response("The collection is empty");
        try {
            Dragon validatedDragon = (Dragon) request.getArgumentCommand();

            if ( validatedDragon.getAge() < getMinAge() ) {
                dragonCollection.addToCollection(validatedDragon);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch ( Exception e ) {
            return new Response(e.toString());
        }
    }


}
