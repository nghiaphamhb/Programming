package Client.Utility;


//import Client.Commands.*;
import Client.Network.Client;
import Client.Utility.DragonGenerator.ByFile.FileInput;
import Client.Utility.DragonGenerator.ByFile.ScriptReader;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon;
import Common.Exception.CommandIsNotFoundException;
import Common.Exception.ScriptRecursionException;
import Common.Network.Request;
import Common.Network.Response;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class CliHandler {
    private Client client;
    private final Logger logger;
    private Input input;


    public CliHandler(Client client, Logger logg, Input input) {
        this.client = client;
        this.logger = logg;
        this.input = input;
    }


    public Response handle(String[] command) {
        Request request = null;
        switch (command[0]) {
            case "add":
            case "add_if_max":
            case "add_if_min":
                Dragon newDragon = input.buildDragon();
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
                request = new Request(command[0]);
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

    public void changeInput(Input newInput) {
        this.input = newInput;
    }

}
