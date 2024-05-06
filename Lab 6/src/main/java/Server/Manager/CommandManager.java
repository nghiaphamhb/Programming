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
        commandHistory = new ArrayDeque<>(13);  // only 13 used command in the history
    }

    /**
     * Initialize a new command into set of commands
     * @param command new command
     */
    public void register( Commands command ) {
        commands.add(command);
    }

    /**
     * Get collection of commands
     * @return collection of commands
     */
    public Set<Commands> getCommands() {
        return commands;
    }

    /**
     * Get history of used command
     * @return history of used command
     */
    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Record the command used in the history
     * @param command used command
     */
    public void addToHistory( String command ) {
        if ( commandHistory.size() == 13 ) {
            commandHistory.poll();
        }
        commandHistory.add(command);
    }

    /**
     * Get a command by his name
     * @param name name of command
     * @return command
     */
    public Commands getByName(String name){
        for (Commands c : commands) {
            if ( c.getName().equals(name)) return c;
        }
        return null;
    }
}
