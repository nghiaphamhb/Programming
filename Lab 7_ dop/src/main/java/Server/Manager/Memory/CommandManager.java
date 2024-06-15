package Server.Manager.Memory;

import Common.Data.User;
import Server.Commands.*;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Manager.Database.DatabaseUserManager;
import Server.Utility.Roles.RoleManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to manager commands
 */
public class CommandManager {
    private final ConcurrentHashMap<Integer, AbstractCommand> commands;
    private final LinkedList<String> commandHistory;
    private final int memoryCapicity = 13;
    private CollectionManager collectionManager;
    private DatabaseUserManager databaseUserManager;
    private DatabaseCollectionManager databaseCollectionManager;
    private RoleManager roleManager;

    public CommandManager(CollectionManager collectionManager, DatabaseUserManager databaseUserManager, DatabaseCollectionManager
            databaseCollectionManager, RoleManager roleManager) {
        commands = new ConcurrentHashMap<>();
        commandHistory = new LinkedList<>();
        this.collectionManager = collectionManager;
        this.databaseUserManager = databaseUserManager;
        this.databaseCollectionManager = databaseCollectionManager;
        this.roleManager = roleManager;
        register();
    }

    /**
     * Get collection of commands
     * @return collection of commands
     */
    public Set<AbstractCommand> getCommands() {
        return new HashSet<>(commands.values());
    }

    public void clearHistory(){
        commandHistory.clear();
    }

    /**
     * Get history of used command
     * @return history of used command
     */
    public List<String> getCommandHistory() {
        synchronized (commandHistory) {
            return new LinkedList<>(commandHistory);
        }
    }

    /**
     * Record the command used in the history
     * @param command used command
     */
    public void addToHistory(String command, User user) {
        synchronized (commandHistory) {
            if (commandHistory.size() == memoryCapicity) {
                commandHistory.removeFirst();
            }
            if (!command.equals("NoSuchCommand") && !command.equals("login") &&
                    !command.equals("register")) commandHistory.addLast(command + " (" + user.getUserName() + ")\n");
        }
    }

    /**
     * Get a command by his name
     * @param name name of command
     * @return command
     */
    public AbstractCommand getByName(String name) {
        for (Map.Entry<Integer, AbstractCommand> entry : commands.entrySet()) {
            if (name.equals(entry.getValue().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Initialize all new command into set of commands
     */
    private void register() {
        int index = 0;
        commands.put(index++, new AddCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new AddIfMaxCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new AddIfMinCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new ClearCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new ExecuteScriptCommand());
        commands.put(index++, new ExitCommand());
        commands.put(index++, new FilterContainsNameCommand(collectionManager));
        commands.put(index++, new HelpCommand(this));
        commands.put(index++, new HistoryCommand(this));
        commands.put(index++, new InfoCommand(collectionManager));
        commands.put(index++, new PrintDescendingCommand(collectionManager));
        commands.put(index++, new PrintFieldDescendingSpeakingCommand(collectionManager));
        commands.put(index++, new RemoveByIdCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new ShowCommand(collectionManager));
        commands.put(index++, new UpdateIdCommand(collectionManager, databaseCollectionManager));
        commands.put(index++, new NoSuchCommand());
        commands.put(index++, new LoginCommand(databaseUserManager));
        commands.put(index++, new RegisterCommand(databaseUserManager));
        commands.put(index++, new ShowUsersCommand(databaseUserManager));
        commands.put(index++, new ChangeRoleCommand(databaseUserManager));
        commands.put(index, new GrantPermissionCommand(databaseUserManager, roleManager));

    }
}
