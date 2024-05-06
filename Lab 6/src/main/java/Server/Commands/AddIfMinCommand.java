package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Data.Dragon;
import Server.Manager.DragonCollection;

/**
 * Command to add a new dragon if his age is maximum in the collection
 */
public class AddIfMinCommand extends Commands {
    private DragonCollection dragonCollection;

    public AddIfMinCommand(DragonCollection dragonCollection) {
        super("add_if_min", "add a new element to the collection, if its value is less than the smallest element of this collection");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Search the youngest dragon in the collection
     * @return minimum age
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
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if ( validatedDragon.getAge() < getMinAge() ) {
                dragonCollection.addToCollection(validatedDragon);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch (CommandSyntaxIsWrongException e ) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }


}
