package Server.Commands;

import Common.Exception.CommandSyntaxIsWrongException;
import Common.Exception.FailureToActWithObjectException;
import Common.Exception.PermissionDeniedException;
import Common.Exception.UserIsNotFoundException;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Database.DatabaseUserManager;
import Server.Utility.Roles.AbstractRole;
import Server.Utility.Roles.CustomRole;
import Server.Utility.Roles.ROLES;

import java.util.NoSuchElementException;

public class GrantPermissionCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public GrantPermissionCommand(DatabaseUserManager databaseUserManager) {
        super("grant_permission", "grant permissions to users (ONLY FOR ADMIN)");
        this.databaseUserManager = databaseUserManager;
    }

    private boolean updateAccess(String strNewAccess, AbstractRole roleToChange){
        if (strNewAccess.length() <= 1) return false;
        String[] elementStrNewAccess = strNewAccess.split("");
        if (!elementStrNewAccess[0].equals("+") && !elementStrNewAccess[0].equals("-")) return false;

        boolean newMode = elementStrNewAccess[0].equals("+");
        if (containsCharacter(elementStrNewAccess, "c")) roleToChange.setCreate(newMode);
        if (containsCharacter(elementStrNewAccess, "u")) roleToChange.setCreate(newMode);
        if (containsCharacter(elementStrNewAccess, "d")) roleToChange.setCreate(newMode);
        if (containsCharacter(elementStrNewAccess, "e")) roleToChange.setCreate(newMode);
        if (containsCharacter(elementStrNewAccess, "r")) roleToChange.setCreate(newMode);
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
    public Response execute(Request request, AbstractRole role) {
        String message = null;
        ProgramCode code = null;
        CustomRole newRole;
        try{
            if (!role.getNameRole().equals(ROLES.ADMIN)) throw new PermissionDeniedException();

            String nameRoleToChange = (String) request.getParameter();
            AbstractRole roleToChange = databaseUserManager.getRoleByNameRole(nameRoleToChange);
            if (roleToChange == null) throw new NoSuchElementException();
            newRole = new CustomRole(roleToChange.getNameRole(), roleToChange.getId(), roleToChange.canCreate(),
                    roleToChange.canUpdate(), roleToChange.canDelete(), roleToChange.canExecute(), roleToChange.canRead());

            String strNewAccess = (String) request.getBonusParameter();
            if (!updateAccess(strNewAccess, newRole)) throw new CommandSyntaxIsWrongException();

            if (databaseUserManager.updateAccessRole(nameRoleToChange, roleToChange.toString())){
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
