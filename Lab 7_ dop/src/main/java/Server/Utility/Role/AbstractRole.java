package Server.Utility.Role;

import java.io.Serializable;

public abstract class AbstractRole implements Serializable {
    private String nameRole;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean execute;
    private boolean read;

    public AbstractRole(String nameRole, boolean create, boolean update, boolean delete, boolean execute, boolean read) {
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

    public boolean canCreate() {
        return create;
    }

    public boolean canUpdate() {
        return update;
    }

    public boolean canDelete() {
        return delete;
    }

    public boolean canExecute() {
        return execute;
    }

    public boolean canRead() {
        return read;
    }
}