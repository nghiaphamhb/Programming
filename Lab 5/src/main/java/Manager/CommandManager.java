package Manager;
import Commands.*;
import java.util.*;

/**
 * This is a class for managing the commands: There are collection's command and history of used commands
 */

public class CommandManager {
    private final Set<Commands> commands;
    private final ArrayDeque<String> commandHistory;

    public CommandManager() {
        commands = new HashSet<>();
        commandHistory = new ArrayDeque<>(13);
    }

    /**
     * Register new command to collection
     * @param command new command
     */
    public void register( Commands command ) {
        commands.add( command );
    }

    /**
     * Get history of used commands
     * @return history of used commands
     */
    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Write a used command to history (note: maximum number of commands in history is 13)
     * @param command used command
     */
    public void addToHistory( String command ) {
        if ( commandHistory.size() == 13 ) {
            commandHistory.poll();
        }
        commandHistory.add(command);
    }

    /**
     * Activate command + write it down in history
     * @param command command which user want to activate
     * @return successfully activate or not
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
}
