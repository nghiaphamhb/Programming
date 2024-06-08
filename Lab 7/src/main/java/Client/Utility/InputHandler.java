package Client.Utility;

import Client.Network.Client;
import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Network.Request;
import Common.Network.Response;

/**
 * Class of input's handle
 */
public class InputHandler {
    private Client client;
    private Input input;


    public InputHandler(Client client, Input input) {
        this.client = client;
        this.input = input;
    }

    /**
     * Get the string from the user( or script), then make a request to the server
     * and get a response from the server
     * @param command string from user
     * @return response from server
     */
    public Response handle(String[] command, User user) {
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
                request = new Request(command[0], newDragon, user);
                break;
            case "clear":
            case "exit":
            case "help":
            case "history":
            case "info":
            case "print_descending":
            case "print_field_descending_speaking":
            case "show":
                request = new Request(command[0], "-1", user);
                if (command[1].isEmpty()) request = new Request(command[0], user);
                break;
            case "execute_script":
            case "filter_contains_name":
                request = new Request(command[0], command[1], user);
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
                request = new Request(command[0], idToRemove, user);
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
                request = new Request(command[0], idToUpdate, updatedDragon, user);
                break;
            default:
                request = new Request("NoSuchCommand", user);
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
//        logger.log(Level.INFO, "The command is accepted. Initialize the Dragon generator... ");
        Display.println("The command is accepted. Initialize the Dragon generator... ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
//            logger.log(Level.SEVERE, "Error exist while initializing the id");
        }
    }
}
