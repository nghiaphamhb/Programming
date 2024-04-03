package Commands;

import Manager.CommandManager;
import Utility.*;

/**
 * Команда для показания списка данных команд со своим описанием
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
                Display.printTable(c.getName(), c.getDescription());
            }
            return true;
        } catch ( IllegalArgumentException e ) {
            Display.printError("Неправильное количество аргументов!");
            Display.println("Использование: " + getName() );
        }
        return false;
    }
}
