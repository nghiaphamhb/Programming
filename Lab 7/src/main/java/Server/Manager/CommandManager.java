package Server.Manager;

import Server.Commands.*;

public class CommandManager extends StandardCommandManager {
    private CollectionManager collectionManager;
    private DatabaseUserManager databaseUserManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public CommandManager(CollectionManager collectionManager, DatabaseUserManager databaseUserManager, DatabaseCollectionManager
                          databaseCollectionManager) {
        super(13);
        this.collectionManager = collectionManager;
        this.databaseUserManager = databaseUserManager;
        this.databaseCollectionManager = databaseCollectionManager;
        register();
    }

    /**
     * Initialize all commands
     */
    public void register() {
        commands.add( new AddCommand(collectionManager, databaseCollectionManager) );
        commands.add( new AddIfMaxCommand(collectionManager, databaseCollectionManager) );
        commands.add( new AddIfMinCommand(collectionManager, databaseCollectionManager) );
        commands.add( new ClearCommand(collectionManager, databaseCollectionManager) );
        commands.add( new ExecuteScriptCommand() );
        commands.add( new ExitCommand() );
        commands.add( new FilterContainsNameCommand(collectionManager) );
        commands.add( new HelpCommand(this) );
        commands.add( new HistoryCommand(this) );
        commands.add( new InfoCommand(collectionManager) );
        commands.add( new PrintDescendingCommand(collectionManager) );
        commands.add( new PrintFieldDescendingSpeakingCommand(collectionManager) );
        commands.add( new RemoveByIdCommand(collectionManager, databaseCollectionManager) );
        commands.add( new ShowCommand(collectionManager) );
        commands.add( new UpdateIdCommand(collectionManager, databaseCollectionManager) );
        commands.add(new NoSuchCommand());
        commands.add(new LoginCommand(databaseUserManager));
        commands.add(new RegisterCommand(databaseUserManager));
    }
}
