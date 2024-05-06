package Client.Utility;

import Client.Network.Client;
import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon;
import Common.Network.Request;
import Common.Network.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class of input's handler
 */
public class CliHandler {
    private Client client;
    private final Logger logger;
    private Input input;


    public CliHandler(Client client, Logger logg, Input input) {
        this.client = client;
        this.logger = logg;
        this.input = input;
    }

    /**
     * Get the string from the user( or script), then make a request to the server
     * and get a response from the server
     * @param command string from user
     * @return response from server
     */
    public Response handle(String[] command) {
        Request request = null;
        switch (command[0]) {
            case "add":
            case "add_if_max":
            case "add_if_min":
                Dragon newDragon = null;
                if (command[1].isEmpty()) {
                    sleep();
                    Display.println("** ID is automatically initialized successfully **");
                    newDragon = input.buildDragon();
                };
                request = new Request(command[0], newDragon);
                break;
            case "clear":
            case "exit":
            case "help":
            case "history":
            case "info":
            case "print_descending":
            case "print_field_descending_speaking":
            case "save":
            case "show":
                request = new Request(command[0], "-1");
                if (command[1].isEmpty()) request = new Request(command[0]);
                break;
            case "execute_script":
            case "filter_contains_name":
                request = new Request(command[0], command[1]);
                break;
            case "remove_by_id":
                long idToRemove = 0;
                if (!command[1].isEmpty()){
                    try {
                        idToRemove = Long.parseLong(command[1]);
                    } catch (NumberFormatException e) {
                        idToRemove = -1;
                    }
                };
                request = new Request(command[0], idToRemove);
                break;
            case "update_id":
                Dragon updatedDragon = null;
                long idToUpdate = 0;
                if (!command[1].isEmpty()){
                    try {
                        idToUpdate = Long.parseLong(command[1]);
                        sleep();
                        Display.println("** Updating the dragon... **");
                        updatedDragon = input.buildDragon();
                    } catch (NumberFormatException e) {
                        idToUpdate = -1;
                    }
                };
                request = new Request(command[0], idToUpdate, updatedDragon);
                break;
            default:
                request = new Request("NoSuchCommand");
                break;
        }
        return client.sendAndReceiveCommand(request);
    }

    /**
     * Change the input between user and script
     * @param newInput type of input
     */
    public void changeInput(Input newInput) {
        this.input = newInput;
    }

    /**
     * Program sleeps while waiting response from server
     */
    public void sleep() {
        logger.log(Level.INFO, "The command is accepted. Initialize the Dragon generator... ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error exist while initializing the id");
        }
    }
}
