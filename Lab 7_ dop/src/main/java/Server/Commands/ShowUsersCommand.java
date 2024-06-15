package Server.Commands;

import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Database.DatabaseUserManager;
import Server.Utility.Roles.AbstractRole;
import Server.Utility.Roles.ROLES;

import java.util.Map;
import java.util.stream.Collectors;

public class ShowUsersCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public ShowUsersCommand(DatabaseUserManager databaseUserManager) {
        super("show_users", "view the entire list of users (ONLY FOR ADMIN)");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public Response execute(Request request, AbstractRole role) {
        String message = null;
        ProgramCode code = null;
        try{
            if (!role.getNameRole().equals(ROLES.ADMIN)) throw new PermissionDeniedException();
            Map<String, String> usersList = databaseUserManager.getUserList();
            message = usersList.entrySet().stream().
                    map(element -> String.format(" %-35s%-1s%n", element.getKey(), element.getValue()))
                    .collect(Collectors.joining());
            code = ProgramCode.OK;
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action.";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
