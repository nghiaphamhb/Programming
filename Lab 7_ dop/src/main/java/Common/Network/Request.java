package Common.Network;

import Common.Data.Dragon.Dragon;
import Common.Data.User;

import java.io.Serializable;

/**
 * Request, which sent from client to server
 */

public class Request implements Serializable {
    private final String nameCommand;
    private Object parameter = null;
    private Object bonusParameter = null;
    private User user;

    /**
     * Make request for command without parameters (command without dragon creation)
     * @param nameCommand name command
     */
    public Request(String nameCommand, User user) {
        this.nameCommand = nameCommand;
        this.user = user;
    }

    /**
     * Make request for command without parameters (command with dragon creation)
     * @param nameCommand name command
     * @param createdDragon created dragon by user or script
     */
    public Request(String nameCommand, Dragon createdDragon, User user) {
        this.nameCommand = nameCommand;
        this.parameter = createdDragon;
        this.user = user;
    }

    /**
     * Make request for command with parameter is dragon name
     * @param nameCommand command name
     * @param nameDragon dragon name
     */
    public Request(String nameCommand, String nameDragon, User user) {
        this.nameCommand = nameCommand;
        this.parameter = nameDragon;
        this.user = user;
    }

    /**
     * Make request for command with parameter is dragon id
     * @param nameCommand command name
     * @param idDragon dragon id
     */
    public Request(String nameCommand, long idDragon, User user) {
        this.nameCommand = nameCommand;
        this.parameter = idDragon;
        this.user = user;
    }

    /**
     * Make request for update_id command
     * @param nameCommand command name
     * @param idDragon dragon id
     */
    public Request(String nameCommand, long idDragon, Dragon updatedDragon, User user) {
        this.nameCommand = nameCommand;
        this.parameter = idDragon;
        this.bonusParameter = updatedDragon;
        this.user = user;
    }

    public Request(String nameCommand, String userName, String nameNewRole, User user) {
        this.nameCommand = nameCommand;
        this.parameter = userName;
        this.bonusParameter = nameNewRole;
        this.user = user;
    }


    public String getNameCommand() {
        return nameCommand;
    }

    public Object getParameter() {
        return parameter;
    }

    public Object getBonusParameter() {
        return bonusParameter;
    }

    public User getUser() {
        return user;
    }

    /**
     *
     * @return information of the request
     */
    @Override
    public String toString() {
        return "Request \"" + nameCommand + "[" + (parameter!=null? parameter.toString(): "empty") + "]["
                + (bonusParameter!=null? bonusParameter.toString():"empty") + "]\" from " + user;
    }
}
