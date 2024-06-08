package Server.Commands;

import Common.Data.User;
import Common.Exception.UserIsNotFoundException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.DatabaseUserManager;

public class LoginCommand extends AbstractCommand {
    private DatabaseUserManager databaseUserManager;

    public LoginCommand(DatabaseUserManager databaseUserManager) {
        super("login", "~hidden");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public Response execute(Request request) {
        String message = "";
        ProgramCode code = null;
        try{
            User user = request.getUser();
            if (databaseUserManager.checkUserByUsernameAndPassword(user)){
                message = "User \'" + user.getUserName() + "\' is accepted.";
                code = ProgramCode.OK;
            } else throw new UserIsNotFoundException();
        } catch (UserIsNotFoundException e) {
            message = "The username or the password is wrong.";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
