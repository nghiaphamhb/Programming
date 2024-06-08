package Client.Utility;

import Client.Network.Client;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Common.Data.User;
import Common.Exception.CommandNotFoundException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;

import java.util.*;

/**
 * Class of program's operation
 */
public class ClientAppRunner implements Runnable{
    private final Client client;
    private final InputHandler inputHandler;
    private final AuthHandler authHandler;
    private ScriptHandler scriptHandler;
    private Input input;
    private boolean isScriptFound;
    private User user;


    public ClientAppRunner(Client client) {
        this.client = client;
        this.input = new Console();
        this.inputHandler = new InputHandler(client, input);
        this.authHandler = new AuthHandler();

    }

    @Override
    public void run(){
        client.connectToServer();
        processAuthentication();
        processRequestToServer();
        client.disconnectToServer();
    }

    private void processAuthentication(){
        Request requestToServer = null;
        Response responseFromServer = null;
        do{
            try{
                requestToServer = authHandler.handle();
                if (requestToServer == null) continue;
                Display.println(requestToServer);
                responseFromServer = client.sendAndReceiveCommand(requestToServer);
                Display.println(responseFromServer);
            } catch (Exception e) {
                Display.printError("Mistake while trying to authenticate user.");
            }
        } while (responseFromServer == null || !responseFromServer.getResponseCode().equals(ProgramCode.OK));
        user = requestToServer.getUser();
    }

    private void processRequestToServer(){
        scriptHandler = new ScriptHandler(inputHandler, input, user);
        isScriptFound = scriptHandler.isScriptFound;
        ProgramCode commandStatus = null;
        do {
            isScriptFound = true;
            try{
                Display.ps1();
                String[] command = argumentToCommand((new Scanner(System.in)).nextLine());

                commandStatus = updateProgramStatus(command);

                if (!isScriptFound) command[1] = "-1";

                if (commandStatus != ProgramCode.ERROR) {
                    Response response = inputHandler.handle(command, user);
                    if (response == null) continue;
                    Display.println("** Response from server: ");
                    Display.println(response);
                }

            } catch (CommandNotFoundException e) {
                Display.printError("This command is not exist. Enter 'help' for helping");
            }
        } while (commandStatus != ProgramCode.CLIENT_EXIT);
    }

    /**
     * Update program's status after get a command from input
     * @param command command
     * @return program's status
     */
    private ProgramCode updateProgramStatus (String[] command)  {
        try {
            switch (command[0]){
                case "exit":
                    if (command[1].isEmpty()) return ProgramCode.CLIENT_EXIT;
                case "execute_script":
                    String filePath = command[1];
                    return scriptHandler.runScript(filePath);
            }
            return ProgramCode.OK;
        } catch (CommandNotFoundException e) {
            if (!command[0].isEmpty()){
                Display.printError("Command " + command[0] + " is not exist. Enter 'help' for helping");
            } else {
                Display.print("");
            }
        }
        return ProgramCode.ERROR;
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