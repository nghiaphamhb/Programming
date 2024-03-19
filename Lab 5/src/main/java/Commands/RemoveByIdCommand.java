package Commands;

import Data.Dragon;
import Manager.DragonManager;
import Exception.*;
import utility.Console;




public class RemoveByIdCommand extends Commands {
    private DragonManager dragonManager;
    public RemoveByIdCommand(DragonManager dragonManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.dragonManager = dragonManager;
    }


    @Override
    public boolean execute(String[] argument) {
        try{
            if (argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            Dragon dragonHasId = filterById(argument[1]);
            if (dragonHasId == null) throw new NullPointerException();
            dragonManager.removeFromCollection( dragonHasId );
            Console.println("Дракон имеющий id " + dragonHasId.getId() + " успешно обезврежен!");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: '" + getName() + " [id]");
        } catch (NullPointerException e){
            Console.printError("Этот id не существует!");
        }
        return false;
    }
    private Dragon filterById (String strIdToFind){
        Dragon dragonToRemove = null;
        long idToFind = Long.valueOf(strIdToFind);
        for (Dragon d : dragonManager.getCollection()){
            if (d.getId().equals(idToFind)) dragonToRemove = d;
        }
        return dragonToRemove;

    }
}
