package utility;

import Manager.*;
import Commands.*;
import Exception.*;
import java.io.*;
import java.util.*;

/**
 * This class manages the program activities
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
     * Connect to file -> Get information from file -> Process information -> Run commands
     * @param filePath path to file script
     * @return status of activity (mode script run complete-fully or not)
     */
    private ExitCode scriptMode (String filePath)  {
        String[] commandLines;
        List<String> commandList = new ArrayList<>();
        String[] command;
        ExitCode commandStatus = null;

        Input.setFileMode();
        try {
            //try to connect to file
            scriptList.add(filePath);
            if (!new File(filePath).exists()) {
                filePath = "src/main/resources/" + filePath;
            }

            //try to get string from file
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            StringBuilder content = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            // Separate the string into command lines
            commandLines = content.toString().split("\\r?\\n");
//            commandLines = content.toString().split("\n");


            // Make list of command lines without empty lines
            for (String commandLine : commandLines) {
                if (!commandLine.trim().isEmpty()) {
                    commandList.add(commandLine);
                }
            }

            //Separate every command lines to 2 parts: keyword + argument
            for (int i = 0; i < commandList.size(); i++) {
                command = (commandList.get(i).trim() + " ").split(" ", 2);

                Console.ps1();
                Console.println( commandList.get(i) );  // "> keyword"

                if (!command[1].isEmpty()) {
                    command[1] = command[1].trim();   // argument
                }

                // Get information from file to make new dragon
                if ((command[0].equals("add") || (command[0].equals("add_if_max") || (command[0].equals("add_if_min")) && command[1].isEmpty()))) {
                    String[] fileScanner = new String[]{
                            commandList.get(i + 1),
                            commandList.get(i + 2),
                            commandList.get(i + 3),
                            commandList.get(i + 4),
                            commandList.get(i + 5),
                            commandList.get(i + 6),
                            commandList.get(i + 7),
                            commandList.get(i + 8)
                    };
                    i += 8;
                    Asker.getInfoFromFile(fileScanner);
                }

                if ( command[0].equals("execute_script") ) {
                    for ( String script : scriptList ) {
                        if ( command[1].equals(script) ) throw new ScriptRecursionException();
                    }
                }
                commandStatus = runCommand(command);
            }
        } catch (IOException e) {
            Console.printError("Этот файл не существует.");
        } catch (ScriptRecursionException e) {
            Console.printError("Скрипты не могут вызываться рекурсивно!");
        }

        Input.setUserMode();
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
                    else return scriptMode(command[1]);
            }
            return ExitCode.RUN;
        } catch (CommandIsNotFoundException e) {
            Console.printError("Команда " + command[0] + " не существует. Наберите 'help' для справки");
        }
        return ExitCode.ERROR;
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
                command = Input.getScanner().nextLine().trim().split(" ", 2);
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

}
