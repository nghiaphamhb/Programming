package utility;

import Manager.*;
import Commands.*;
import Exception.*;
import java.io.*;
import java.util.*;

/**
 * Runs the program
 */
public class Runner {
    public enum ExitCode {
        RUN,
        ERROR,
        EXIT
    }
    private final CommandManager commandManager;
    private DragonManager dragonManager;
    private List<String> scriptList; //List of scripts used


    public Runner(CommandManager commandManager, DragonManager dragonManager) {
        this.commandManager = commandManager;
        this.dragonManager = dragonManager;
        this.scriptList  = new ArrayList<>();
        CommandRegister();
    }

    /**
     * Automatically start and run the program
     */

    public void start(){
        ExitCode commandStatus = null;
        String[] command;
        do {
            try {
                Console.ps1();
                command = Asker.getScanner().nextLine().trim().split(" ", 2);
                if ( command[0].isEmpty() ) throw new NoSuchElementException();
                if (command.length > 1) command[1] = command[1].trim();

                commandStatus = runCommand(command);
            } catch (NoSuchElementException exception) {
                Console.print("");
            } catch (CommandIsNotFoundException e) {
                Console.printError("Эта команда не существует. Наберите 'help' для справки");
            }
        } while (commandStatus != ExitCode.EXIT);
    }
    /**
     * Register all commands
     */

    private void CommandRegister (){
        this.commandManager.register( new AddCommand(dragonManager) );
        this.commandManager.register( new AddIfMaxCommand(dragonManager) );
        this.commandManager.register( new AddIfMinCommand(dragonManager) );
        this.commandManager.register( new ClearCommand(dragonManager) );
        this.commandManager.register( new ExecuteScriptCommand() );
        this.commandManager.register( new ExitCommand() );
        this.commandManager.register( new FilterContainsNameCommand(dragonManager) );
        this.commandManager.register( new HelpCommand(commandManager) );
        this.commandManager.register( new HistoryCommand(commandManager) );
        this.commandManager.register( new InfoCommand(dragonManager) );
        this.commandManager.register( new PrintDescendingCommand(dragonManager) );
        this.commandManager.register( new PrintFieldDescendingSpeakingCommand(dragonManager) );
        this.commandManager.register( new RemoveByIdCommand(dragonManager) );
        this.commandManager.register( new SaveCommand(dragonManager) );
        this.commandManager.register( new ShowCommand(dragonManager) );
        this.commandManager.register( new UpdateIdCommand(dragonManager) );
    }

    /**
     * Run script file (Connect to file -> Get information from file -> Process information -> Run commands)
     * @param filePath path to file script
     * @return status of activity (mode script run complete-fully or not)
     */
    private ExitCode runScript (String filePath)  {
        List<String> scriptLines;
        String[] command;
        ExitCode commandStatus = null;

        InputMode.setFileMode();
        try {
            //register file's path to history
            scriptList.add(filePath);

            //Connect to file and get lines from script file
            ScriptReader.setInputStream(filePath);
            scriptLines = ScriptReader.getScriptLines();

            //Separate every command lines to 2 parts: keyword + argument
            for (int i = 0; i < scriptLines.size(); i++) {
                command = (scriptLines.get(i).trim() + " ").split(" ", 2);

                Console.ps1();
                Console.println( scriptLines.get(i) );  // "> keyword"

                if (!command[1].isEmpty()) {
                    command[1] = command[1].trim();   // argument
                }

                // Get dragon's information from file
                if ( Arrays.asList( "add", "add_if_max", "add_if_min" ).contains( command[0] ) && command[1].isEmpty() ) {
                    String[] info = scriptLines.subList(i+1,  i+9).toArray( new String[8] );
                    Asker.getInfoFromFile(info);
                    i += 8;
                }

                if ( command[0].equals("execute_script") ) {
                    for ( String script : scriptList ) {
                        if ( command[1].equals(script) ) throw new ScriptRecursionException();
                    }
                }

                commandStatus = runCommand(command);
            }
        } catch (ScriptRecursionException e) {
            Console.printError("Скрипты не могут вызываться рекурсивно!");
        }

        InputMode.setUserMode();
        return commandStatus;
    }

    /**
     * Activate command and update status of program
     * @param command command want to be run
     * @return status of program (RUN/ERROR/EXIT)
     * @throws CommandIsNotFoundException didn't found the command
     */
    private ExitCode runCommand (String[] command)  {
        try {
            if ( !commandManager.activateCommand(command) ) throw new CommandIsNotFoundException();
            switch (command[0]){
                case "exit":
                    if (command.length == 1) return ExitCode.EXIT;
                    else return ExitCode.ERROR;
                case "execute_script":
                    if (command.length == 1) return ExitCode.ERROR;
                    else return runScript(command[1]);
            }
            return ExitCode.RUN;
        } catch (CommandIsNotFoundException e) {
            Console.printError("Команда " + command[0] + " не существует. Наберите 'help' для справки");
        }
        return ExitCode.ERROR;
    }

}
