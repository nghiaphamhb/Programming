package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToActWithObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Exception.UserIsNotFoundException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Database.DatabaseUserManager;
import Server.Utility.Role;
import Server.Utility.Enum.ROLES;

/**
 * Command for only admin to change role of user
 */
public class ChangeRoleCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;
    public ChangeRoleCommand(DatabaseUserManager databaseUserManager) {
        super("change_role", "change user's role (ONLY FOR ADMIN)");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public Response execute(Request request, Role role) {
        String message;
        ProgramCode code;
        try{
            if (!role.getNameRole().equals(ROLES.ADMIN)) throw new PermissionDeniedException();
            String usernameToChange = (String) request.getParameter();
            String nameNewRole = (String) request.getBonusParameter();
            if (usernameToChange.isEmpty() || nameNewRole.isEmpty()) throw new CommandSyntaxIsWrongException();

            if (!databaseUserManager.checkUserByUsername(usernameToChange)) throw new UserIsNotFoundException();
            if (!nameNewRole.equals(ROLES.ADMIN)&&!nameNewRole.equals(ROLES.CLEANER)&&
                    !nameNewRole.equals(ROLES.ANALYST)&&!nameNewRole.equals(ROLES.CREATOR)&&
                    !nameNewRole.equals(ROLES.DEVELOPER)&&!nameNewRole.equals(ROLES.TESTER))
                throw new CommandSyntaxIsWrongException();

            if (databaseUserManager.changeUserRole(usernameToChange, nameNewRole.toLowerCase())){
                message = "Success changes the role of this user.";
                code = ProgramCode.OK;
            } else throw new FailureToActWithObjectException();
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action.";
            code = ProgramCode.ERROR;
        } catch (FailureToActWithObjectException e) {
            message = "Syntax command is not correct. Usage: \"" + getName() + "\"";
            code = ProgramCode.ERROR;
        } catch (UserIsNotFoundException e) {
            message = "The user is not exists.";
            code = ProgramCode.ERROR;
        } catch (CommandSyntaxIsWrongException e){
            message = "This role is not exists. Roles include: { " + ROLES.ADMIN + ", " +
                    ROLES.CLEANER + ", " +
                    ROLES.ANALYST + ", " +
                    ROLES.CREATOR + ", " +
                    ROLES.DEVELOPER + ", " +
                    ROLES.TESTER + " }";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
