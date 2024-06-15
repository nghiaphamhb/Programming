package Client.Utility;

import Client.Network.Client;
import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Common.Network.Request;
import Common.Network.Response;

import java.util.Scanner;

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
            case "show_users":
                request = new Request(command[0], user);
                break;
            case "change_role":
                Display.println("The user needs to be updated:");
                Display.ps2();
                String userName = new Scanner(System.in).nextLine();
                Display.println("The user's role needs to be updated:");
                Display.ps2();
                String nameRole = new Scanner(System.in).nextLine();
                request = new Request(command[0], userName, nameRole, user);
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
            case "grant_permission":
                if (!command[1].isEmpty()) {
                    String[] parts = splitString(command[1]);
                    request = new Request(command[0], parts[0], parts[1], user);
                    break;
                }

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
        Display.println("Initialize the Dragon generator... ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Display.printError( "Error exist while initializing the id");
        }
    }

    public String[] splitString(String strInput) {
        String[] parts = strInput.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
