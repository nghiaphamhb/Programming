package Server.Manager;

import Server.Commands.*;

public class CommandManager extends StandardCommandManager {
    private DragonCollection dragonCollection;

    public CommandManager(DragonCollection dragonCollection) {
        super(13);
        this.dragonCollection = dragonCollection;
        register();
    }

    /**
     * Initialize all commands
     */
    public void register() {
        commands.add( new AddCommand(dragonCollection) );
        commands.add( new AddIfMaxCommand(dragonCollection) );
        commands.add( new AddIfMinCommand(dragonCollection) );
        commands.add( new ClearCommand(dragonCollection) );
        commands.add( new ExecuteScriptCommand() );
        commands.add( new ExitCommand() );
        commands.add( new FilterContainsNameCommand(dragonCollection) );
        commands.add( new HelpCommand(this) );
        commands.add( new HistoryCommand(this) );
        commands.add( new InfoCommand(dragonCollection) );
        commands.add( new PrintDescendingCommand(dragonCollection) );
        commands.add( new PrintFieldDescendingSpeakingCommand(dragonCollection) );
        commands.add( new RemoveByIdCommand(dragonCollection) );
        commands.add( new ShowCommand(dragonCollection) );
        commands.add( new UpdateIdCommand(dragonCollection) );
        commands.add(new NoSuchCommand());
    }
}
