package Client.Utility;

import Common.Network.Request;
import Common.Data.User;

public class AuthHandler {
    private final String loginCommand ;
    private final String registerCommand;

    public AuthHandler() {
        loginCommand = "login";
        registerCommand = "register";
    }

    public Request handle(){
        String nameCommand = AuthAsker.askIfRegistered() ? loginCommand : registerCommand;
        User user = new User(AuthAsker.askUsername(), AuthAsker.askPassword());
        return new Request(nameCommand, user);
    }
}
