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
        ProgramCode programStatus = null;
        while(true) {
            try{
                //Get input from user
                Display.ps1();
                String[] command = argumentToCommand((new Scanner(System.in)).nextLine());

                //If command is "execute script", check if this script if exist
                if (command[0].equals("execute_script") &&
                        !scriptHandler.findScript(command[1])) command[1] = "cannot find";
                //send request and get response from server
                Response response = inputHandler.handle(command, user);
                //Get program status
                programStatus = response.getResponseCode();

                //If it's execute_script command, run script
                if (programStatus != ProgramCode.ERROR && command[0].equals("execute_script"))
                    scriptHandler.runScript(command[1]);
                //If it's exit command
                if (programStatus == ProgramCode.CLIENT_EXIT) break;

                Display.println("** Response from server: ");
                Display.println(response);
            } catch (CommandNotFoundException e) {
                Display.printError("This command is not exist. Enter 'help' for helping");
            }
        }
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