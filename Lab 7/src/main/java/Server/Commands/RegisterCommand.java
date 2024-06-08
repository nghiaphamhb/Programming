package Server.Commands;

import Common.Data.User;
import Common.Exception.UserAlreadyExistsException;
import Common.Network.Request;
import Common.Network.Response;
import Common.Network.ProgramCode;
import Server.Manager.DatabaseUserManager;

public class RegisterCommand extends AbstractCommand {
    private DatabaseUserManager databaseUserManager;

    public RegisterCommand(DatabaseUserManager databaseUserManager) {
        super("register", "~hidden");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public Response execute(Request request) {
        String message = "";
        try{
            User user = request.getUser();
            if (!databaseUserManager.insertUser(user)) throw new UserAlreadyExistsException();
            message = "User \'" + user.getUserName() + "\' with password \'" + "*".repeat(user.getPassword().length())
                    + "\' was registered.";
        } catch (UserAlreadyExistsException e) {
            message = "This user is already exists.";
        }
        return new Response(message, ProgramCode.ERROR);
    }
}
