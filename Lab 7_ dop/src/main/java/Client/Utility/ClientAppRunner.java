package Client.Utility;

import Client.Network.Client;
import Client.Utility.Authentication.AuthHandler;
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
    private User user;


    public ClientAppRunner(Client client) {
        this.client = client;
        this.input = new Console();
        this.inputHandler = new InputHandler(client, input);
        this.authHandler = new AuthHandler();

    }

    /**
     * Run
     */
    @Override
    public void run(){
        client.connectToServer();
        processAuthentication();
        processRequestToServer();
        client.disconnectToServer();
    }

    /**
     * Process authentication
     */
    private void processAuthentication(){
        Request requestToServer = null;
        Response responseFromServer = null;
        do{
            try{
                requestToServer = authHandler.handle();
                if (requestToServer == null) continue;
                responseFromServer = client.sendAndReceiveCommand(requestToServer);
                if (responseFromServer.getMessage() == null) throw new Exception();
                Display.println(responseFromServer);
            } catch (Exception e) {
                Display.printError("Mistake while trying to authenticate user.");
            }
        } while (responseFromServer == null || !responseFromServer.getResponseCode().equals(ProgramCode.OK));
        user = requestToServer.getUser();
    }

    /**
     * Process request to server
     */
    private void processRequestToServer(){
        scriptHandler = new ScriptHandler(inputHandler, input, user);
        ProgramCode commandStatus = null;
        do {
            ScriptHandler.runScript = true;
            try{
                Display.ps1();
                String[] command = argumentToCommand((new Scanner(System.in)).nextLine());

                if (!scriptHandler.findScript(command[1])) command[1] = "cannot find";
                Response response = inputHandler.handle(command, user);
                if (response.getResponseCode() == ProgramCode.ERROR) ScriptHandler.runScript = false;
                commandStatus = updateProgramStatus(command);
                Display.println("** Response from server: ");
                Display.println(response);
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
                    if (ScriptHandler.runScript) return scriptHandler.runScript(filePath);
                    return ProgramCode.ERROR;
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