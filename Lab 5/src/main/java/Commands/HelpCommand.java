package Commands;

import Manager.CommandManager;
import utility.*;
import Exception.*;

/**
 * This command shows all commands with its description in the format of table
 */
public class HelpCommand extends Commands {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute (String[] argument){
        try {
            if ( !argument[1].isEmpty() ) throw new IllegalArgumentException();
            for ( Commands c : commandManager.getCommands() ){
                Console.printTable(c.getName(), c.getDescription());
            }
            return true;
        } catch ( IllegalArgumentException e ) {
            Console.printError("Неправильное количество аргументов!");
            Console.println("Использование: " + getName() );
        }
        return false;
    }
}
