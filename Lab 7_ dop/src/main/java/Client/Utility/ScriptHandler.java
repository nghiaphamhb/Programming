package Client.Utility;

import Client.Utility.DragonGenerator.ByFile.FileInput;
import Client.Utility.DragonGenerator.ByFile.ScriptReader;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Common.Data.User;
import Common.Exception.ScriptRecursionException;
import Common.Network.ProgramCode;
import Common.Network.Response;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ScriptHandler {
    private final InputHandler inputHandler;
    private final List<String> scriptList;
    private Input input;
    public static boolean runScript = true;
    private final User user;

    public ScriptHandler(InputHandler inputHandler, Input input, User user) {
        this.inputHandler = inputHandler;
        this.scriptList = new ArrayList<>();
        this.input = input;
        this.user = user;
    }

    public boolean findScript(String filePath){
        runScript = true;
        try{
            if (filePath.isEmpty()) throw new FileNotFoundException();
            ScriptReader.setInputStream(filePath); //i throw an exception FileIsNotFound
        } catch (FileNotFoundException e) {
            runScript = false;
        }
        return runScript;
    }

    /**
     * Run the script if the script is valid
     * @param filePath file's path
     */
    public void runScript (String filePath)  {
        Response firstResponse = inputHandler.handle(new String[]{"execute_script", filePath}, user);
        Display.println(firstResponse);

        //register file's path to history
        scriptList.add(filePath);

        // execute lines in the script
        executeScriptLines(ScriptReader.getScriptLines());
        updateInput(new Console());
    }

    /**
     * Execute lines in the script
     * @param scriptLines lines in the script
     */
    private void executeScriptLines(List<String> scriptLines){
        String[] command;
        ProgramCode commandStatus = null;
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

                Response response = inputHandler.handle(command, user);
                Display.println(response);
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
        this.inputHandler.changeInput(input);
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
}
