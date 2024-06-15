package Server.Utility.Roles;

public class CustomRole extends AbstractRole{
    public CustomRole(String nameRole, long id, boolean create, boolean update, boolean delete, boolean execute, boolean read) {
        super(nameRole, id, create, update, delete, execute, read);
    }
}
