package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Exception.*;
import utility.Console;

/**
 * This command removes a dragon by its ID
 */
public class RemoveByIdCommand extends Commands {
    private DragonManager dragonManager;
    public RemoveByIdCommand(DragonManager dragonManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.dragonManager = dragonManager;
    }


    @Override
    public boolean execute(String[] argument) {
        try{
            if (argument[1].isEmpty()) throw new IllegalArgumentException();

            long id = Long.valueOf(argument[1]);
            Dragon dragon = dragonManager.getById(id);

            if (dragon == null) throw new NullPointerException();

            dragonManager.removeFromCollection( dragon );
            Console.println("Дракон имеющий id " + id + " успешно обезврежен!");
            return true;
        } catch (IllegalArgumentException e){
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + " [id]");
        } catch (NullPointerException e){
            Console.printError("Этот id не существует!");
        }
        return false;
    }

}
