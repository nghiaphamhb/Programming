package Common.Data.Role;

import java.io.Serializable;

public abstract class Roles implements Serializable {
    private String nameRole;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean execute;
    private boolean read;

    public Roles(String nameRole, boolean create, boolean update, boolean delete, boolean execute, boolean read) {
        this.nameRole = nameRole;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.execute = execute;
        this.read = read;
    }

    public String getNameRole() {
        return nameRole;
    }
}