package Commands;

import Manager.DragonManager;
import Utility.Display;

import java.time.LocalDateTime;

/**
 * Команда для показания информации у данной коллекции
 */
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
            if (!argument[1].isEmpty()) throw new IllegalArgumentException();
            LocalDateTime createTime = dragonManager.getCreateTime();
            String creatTimeString = (createTime == null) ? "в данной сессии инициализации еще не происходило" :
                    createTime.toLocalDate().toString() + " " + createTime.toLocalTime().toString();

            LocalDateTime saveTime = dragonManager.getSaveTime();
            String saveTimeString = (saveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    saveTime.toLocalDate().toString() + " " + saveTime.toLocalTime().toString();

            Display.println("Сведения о коллекции:");
            Display.println(" Тип: " + dragonManager.getCollectionType());
            Display.println(" Количество элементов: " + dragonManager.getCollectionSize());
            Display.println(" Дата последнего сохранения: " + saveTimeString);
            Display.println(" Дата последней инициализации: " + creatTimeString);
            return true;
        } catch (IllegalArgumentException exception) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
