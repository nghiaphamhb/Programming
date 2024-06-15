package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToActWithObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Database.DatabaseUserManager;
import Server.ServerApp;
import Server.Utility.Role;
import Server.Utility.Enum.ROLES;
import Server.Manager.Database.DatabaseRoleManager;

import java.util.NoSuchElementException;
import java.util.logging.Level;

public class GrantPermissionCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;
    private DatabaseRoleManager databaseRoleManager;

    public GrantPermissionCommand(DatabaseUserManager databaseUserManager, DatabaseRoleManager databaseRoleManager) {
        super("grant_permission", "grant permissions to users (ONLY FOR ADMIN)");
        this.databaseUserManager = databaseUserManager;
        this.databaseRoleManager = databaseRoleManager;
    }

    private boolean updateAccess(String strNewAccess, Role roleToChange){
        if (strNewAccess.length() <= 1) return false;
        String[] elementStrNewAccess = strNewAccess.split("");
        if (!elementStrNewAccess[0].equals("+") && !elementStrNewAccess[0].equals("-")) return false;

        boolean newMode = elementStrNewAccess[0].equals("+");
        if (containsCharacter(elementStrNewAccess, "c")) roleToChange.setCreate(newMode);
        if (containsCharacter(elementStrNewAccess, "u")) roleToChange.setUpdate(newMode);
        if (containsCharacter(elementStrNewAccess, "d")) roleToChange.setDelete(newMode);
        if (containsCharacter(elementStrNewAccess, "e")) roleToChange.setExecute(newMode);
        if (containsCharacter(elementStrNewAccess, "r")) roleToChange.setRead(newMode);
        ServerApp.logger.log(Level.INFO, "New access of role \'" + roleToChange.getNameRole() + "\'' is \"" +
                roleToChange.toString() + "\"");
        return true;
    }

    private boolean containsCharacter(String[] array, String character) {
        for (String s : array) {
            if (s.equals(character)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response execute(Request request, Role role) {
        String message = null;
        ProgramCode code = null;
        try{
            if (!role.getNameRole().equals(ROLES.ADMIN)) throw new PermissionDeniedException();

            String nameRoleToChange = (String) request.getParameter();
            Role roleToChange = databaseRoleManager.getRoleByNameRole(nameRoleToChange);
            if (roleToChange == null) throw new NoSuchElementException();

            String strNewAccess = (String) request.getBonusParameter();
            if (!updateAccess(strNewAccess, roleToChange)) throw new CommandSyntaxIsWrongException();

            if (databaseRoleManager.updateAccessRole(nameRoleToChange, roleToChange.toString())){
                message = "Success changes the access of this role.";
                code = ProgramCode.OK;
            } else throw new FailureToActWithObjectException();
        } catch (PermissionDeniedException e) {
            message = "Not enough permissions to do this action.";
            code = ProgramCode.ERROR;
        } catch (CommandSyntaxIsWrongException e) {
            message = "Syntax command is not correct. Usage: " + getName() + " [nameRole] [(+/-)(cuder)]";
            code = ProgramCode.ERROR;
        } catch (FailureToActWithObjectException e) {
            message = "Failure to update access of this role.";
            code = ProgramCode.ERROR;
        } catch (NoSuchElementException e){
            message = "This role is not exists.";
            code = ProgramCode.ERROR;
        }
        return new Response(message, code);
    }
}
