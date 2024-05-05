package Server.Manager;
import Server.Commands.*;
import java.util.*;

/**
 * Command Collection management: command collection + history (list of commands used)
 */

public class CommandManager {
    private final Set<Commands> commands;
    private final ArrayDeque<String> commandHistory;

    public CommandManager() {
        commands = new HashSet<>();
        commandHistory = new ArrayDeque<>(13);
    }

    /**
     * Инициализировать новую команду
     * @param command новая команда
     */
    public void register( Commands command ) {
        commands.add(command);
    }

    /**
     * Вернуть коллекцию команд
     * @return коллекция команд
     */
    public Set<Commands> getCommands() {
        return commands;
    }

    /**
     * Вернуть список использованных команд
     * @return список использованных команд
     */
    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Записать использованную команду в истории
     * @param command использованная команда
     */
    public void addToHistory( String command ) {
        if ( commandHistory.size() == 13 ) {
            commandHistory.poll();
        }
        commandHistory.add(command);
    }



    public void clear() {
        commands.clear();
    }

    public Commands getByName(String name){
        for (Commands c : commands) {
            if ( c.getName().equals(name)) return c;
        }
        return null;
    }
}
