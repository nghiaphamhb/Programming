package Manager;
import Commands.*;

import java.util.*;

// this is a class for managing the commands
public class CommandManager {
    private final Set<Commands> commands;
    private final ArrayDeque<String> commandHistory;

    public CommandManager() {
        commands = new HashSet<>();
        commandHistory = new ArrayDeque<>(13);
    }

    //ham them command
    public void register(Commands command) {
        commands.add(command);
    }

    public boolean activateCommand(String[] command) {

        String nameCommand = command[0];
        for (Commands c : commands) {
            if (c.getName().equals(nameCommand)) {
                if (command.length == 1) {
                    String[] tmpCommand = Arrays.copyOf(command, command.length + 1);
                    tmpCommand[command.length] = "";
                    c.execute(tmpCommand);
                } else {
                    c.execute(command);
                }
                addToHistory(nameCommand);
                return true;
            }
        }
        return false;
    }

    public Set<Commands> getCommands() {
        return commands;
    }

    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    private void addToHistory(String command) {
        if (commandHistory.size() == 13) {
            commandHistory.poll();
        }
        commandHistory.add(command);
    }


}
