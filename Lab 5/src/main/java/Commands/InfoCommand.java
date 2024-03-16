package Commands;

import Data.*;
import Manager.DragonManager;
import Exception.*;
import utility.Console;

import java.time.LocalDateTime;
import java.util.Set;

public class InfoCommand extends Commands {
    private final DragonManager dragonManager;
    public InfoCommand(DragonManager dragonManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)");
        this.dragonManager = dragonManager;
    }
    @Override
    public boolean execute(String[] argument){
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime createTime = dragonManager.getCreateTime();
            String creatTimeString = (createTime == null) ? "в данной сессии инициализации еще не происходило" :
                    createTime.toLocalDate().toString() + " " + createTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = dragonManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Сведения о коллекции:");
            Console.println(" Тип: " + dragonManager.getCollectionType());
            Console.println(" Количество элементов: " + dragonManager.getCollectionSize());
            Console.println(" Дата последнего сохранения: " + lastSaveTimeString);
            Console.println(" Дата последней инициализации: " + creatTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
