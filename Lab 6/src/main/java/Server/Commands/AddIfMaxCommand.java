package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.DragonCollection;
import Common.Data.*;

/**
 * Command to add a new dragon if his age is maximum in the collection
 */
public class AddIfMaxCommand extends Commands {
    private DragonCollection dragonCollection;

    public AddIfMaxCommand(DragonCollection dragonCollection) {
        super("add_if_max", "add a new element to the collection, if its value is greater than the value of the largest element of this collection");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Search the oldest dragon in the collection
     * @return maximum age
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
            if (validatedDragon == null) throw new CommandSyntaxIsWrongException();
            if ( validatedDragon.getAge() > getMaxAge() ) {
                dragonCollection.addToCollection(validatedDragon);
                return new Response("The dragon has been successfully added! ");
            }
            return new Response("Could not add that dragon!");
        } catch (CommandSyntaxIsWrongException e ) {
            return new Response("Syntax command is not correct. Usage: \"" + getName() + "\"");
        }
    }





}
