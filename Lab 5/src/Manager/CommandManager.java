package Manager;
import Commands.*;
import java.util.*;

/**
 * Управление командной коллекции: коллекция команд + история (список использованных команд)
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
        commands.add( command );
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

    /**
     * Командная операция: Выполнить действие команды и записать ее в историю
     * @param command команда
     * @return есть ли успешно
     */
    public boolean activateCommand( String[] command ) {
        String nameCommand = command[0];
        for ( Commands c : commands ) {
            if ( c.getName().equals( nameCommand ) ) {
                if ( command.length == 1 ) {
                    String[] tmpCommand = Arrays.copyOf( command, command.length + 1 );
                    tmpCommand[ command.length ] = "";
                    c.execute( tmpCommand );
                } else {
                    c.execute( command );
                }
                addToHistory( nameCommand );
                return true;
            }
        }
        return false;
    }

    public void clear() {
        commands.clear();
    }
}
