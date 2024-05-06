package Common.Network;

import Common.Data.Dragon;
import java.io.Serializable;

/**
 * Request, which sent from client to server
 */

public class Request implements Serializable {
    private final String nameCommand;
    private Object parameter = null;
    private Dragon updatedDragon = null;  //only update_id command use him

    /**
     * Make request for command without parameters (command without dragon creation)
     * @param nameCommand name command
     */
    public Request(String nameCommand) {
        this.nameCommand = nameCommand;
    }

    /**
     * Make request for command without parameters (command with dragon creation)
     * @param nameCommand name command
     * @param createdDragon created dragon by user or script
     */
    public Request(String nameCommand, Dragon createdDragon) {
        this.nameCommand = nameCommand;
        this.parameter = createdDragon;
    }

    /**
     * Make request for command with parameter is dragon name
     * @param nameCommand command name
     * @param nameDragon dragon name
     */
    public Request(String nameCommand, String nameDragon) {
        this.nameCommand = nameCommand;
        this.parameter = nameDragon;
    }

    /**
     * Make request for command with parameter is dragon id
     * @param nameCommand command name
     * @param idDragon dragon id
     */
    public Request(String nameCommand, long idDragon) {
        this.nameCommand = nameCommand;
        this.parameter = idDragon;
    }

    /**
     * Make request for update_id command
     * @param nameCommand command name
     * @param idDragon dragon id
     */
    public Request(String nameCommand, long idDragon, Dragon updatedDragon) {
        this.nameCommand = nameCommand;
        this.parameter = idDragon;
        this.updatedDragon = updatedDragon;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public Object getArgumentCommand() {
        return parameter;
    }

    public Dragon getUpdatedDragon() {
        return updatedDragon;
    }

    /**
     *
     * @return information of the request
     */
    @Override
    public String toString() {
        return "Request \"" + nameCommand + "[" + (parameter!=null? parameter.toString(): " ") + "]["
                + (updatedDragon!=null? parameter.toString():" ") + "]\"";
    }
}
