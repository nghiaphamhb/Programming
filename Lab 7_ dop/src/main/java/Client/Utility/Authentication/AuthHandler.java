package Client.Utility.Authentication;

import Common.Network.Request;
import Common.Data.User;

/**
 * Class to classify type of authentication
 */
public class AuthHandler {
    private final String loginCommand ;
    private final String registerCommand;

    public AuthHandler() {
        loginCommand = "login";
        registerCommand = "register";
    }

    /**
     * ask type of authentication
     * @return request to server
     */
    public Request handle(){
        String nameCommand = AuthAsker.askIfRegistered() ? loginCommand : registerCommand;
        User user = new User(AuthAsker.askUsername(), AuthAsker.askPassword());
        return new Request(nameCommand, user);
    }
}
