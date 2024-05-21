package Client.Utility;

import Client.Network.Client;
import Client.Utility.DragonGenerator.ByFile.FileInput;
import Client.Utility.DragonGenerator.ByFile.ScriptReader;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Common.Exception.CommandNotFoundException;
import Common.Exception.ScriptRecursionException;
import Common.Network.Response;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class of program's operation
 */
public class CliRunner implements Runnable{
    private final Client client;
    private final Logger logger;
    private final CliHandler cliHandler;
    private Input input;
    private List<String> scriptList; //List of scripts used
    private boolean isScriptFound = true;


    public CliRunner(Client client, Logger logger) {
        this.client = client;
        this.logger = logger;
        this.input = new Console();
        this.cliHandler = new CliHandler(client, logger, input);
        this.scriptList = new ArrayList<>();
    }

    @Override
    public void run(){
        ProgramStatus commandStatus = null;
        do {
            isScriptFound = true;
            try {
                Display.ps1();
                Scanner scanner = new Scanner(System.in);
                String[] command = argumentToCommand(scanner.nextLine());

                commandStatus = updateProgramStatus(command);

                if (!isScriptFound) {
                    command[1] = "-1";
                }

                if (commandStatus != ProgramStatus.ERROR) {
                    Response response = cliHandler.handle(command);
                    if (response == null) continue;
                    Display.println(response);
                }

            } catch (CommandNotFoundException e) {
                Display.printError("This command is not exist. Enter 'help' for helping");
            }
        } while (commandStatus != ProgramStatus.EXIT);
        client.disconnectToServer();
        logger.log(Level.INFO, "Program was ended.");
    }

    /**
     * Update program's status after get a command from input
     * @param command command
     * @return program's status
     */
    private ProgramStatus updateProgramStatus (String[] command)  {
        try {
            switch (command[0]){
                case "exit":
                    if (command[1].isEmpty()) return ProgramStatus.EXIT;
                case "execute_script":
                    return runScript(command[1]);
            }
            return ProgramStatus.RUN;
        } catch (CommandNotFoundException e) {
            if (!command[0].isEmpty()){
                Display.printError("Command " + command[0] + " is not exist. Enter 'help' for helping");
            } else {
                Display.print("");
            }
        }
        return ProgramStatus.ERROR;
    }

    /**
     * Process a string to array with length 2
     * @param argument a string
     * @return array
     */
    private String[] argumentToCommand(String argument){
        String[] command = {};
        try{
            command = (argument.trim() + " ").split(" ", 2);
            if ( command[0].isEmpty() ) throw new NoSuchElementException();
            if (command.length > 1) command[1] = command[1].trim();
        } catch (NoSuchElementException exception) {
            Display.print("");
        }
        return command;
    }

    /**
     * Run the script if the script is valid
     * @param filePath file's path
     * @return program's status after running script
     */
    private ProgramStatus runScript (String filePath)  {
        if (filePath.isEmpty()) return ProgramStatus.RUN;
        try {
            ScriptReader.setLogger(logger);

            //Try to connect to file
            ScriptReader.setInputStream(filePath); //if did not find filePath, will throw an exception
            Response firstResponse = cliHandler.handle(new String[]{"execute_script", filePath});
            Display.println(firstResponse);

            //register file's path to history
            scriptList.add(filePath);

            // execute lines in the script
            executeScriptLines(ScriptReader.getScriptLines());
        } catch (FileNotFoundException e) {
            isScriptFound = false;
            return ProgramStatus.RUN;
        }
        updateInput(new Console());
        return ProgramStatus.ERROR;
    }

    /**
     * Execute lines in the script
     * @param scriptLines lines in the script
     */
    private void executeScriptLines(List<String> scriptLines){
        String[] command;
        ProgramStatus commandStatus = null;
        try{
            //Separate every command lines to 2 parts: keyword + argument
            for (int i = 0; i < scriptLines.size(); i++) {
                command = argumentToCommand(scriptLines.get(i));

                Display.ps1();
                Display.println(scriptLines.get(i));  // "> keyword"

                // Get dragon's information from file
                if (Arrays.asList( "add", "add_if_max", "add_if_min" ).contains( command[0] ) && command[1].isEmpty() ) {
                    String[] info = scriptLines.subList(i+1,  i+9).toArray( new String[8] );
                    updateInput(new FileInput(info));
                    i += 8; // because the amount of information needed to create a dragon is 8
                }

                //check if script recurse
                if ( command[0].equals("execute_script") ) {
                    for ( String script : scriptList ) {
                        if ( command[1].equals(script) ) throw new ScriptRecursionException();
                    }
                }

                Response response = cliHandler.handle(command);
                Display.println(response);

                commandStatus = updateProgramStatus(command);
            }
        } catch (ScriptRecursionException e) {
            Display.printError("Scripts cannot be called recursively!");
        }
    }

    /**
     * Change the input
     * @param input user/ script
     */
    private void updateInput(Input input) {
        this.input = input;
        this.cliHandler.changeInput(input);
    }

}