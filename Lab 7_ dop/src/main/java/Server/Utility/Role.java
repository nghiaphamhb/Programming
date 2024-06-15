package Server.Utility;

public class Role {
    private final String nameRole;
//    private final long id;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean execute;
    private boolean read;

    public Role(String nameRole, boolean create, boolean update, boolean delete, boolean execute, boolean read) {
//        this.id = id;
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

//    public long getId() {
//        return id;
//    }

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

    public void setCreate(boolean create) {
        this.create = create;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        String access = (canCreate() ? "c" : "-") +
                (canUpdate() ? "u" : "-") +
                (canDelete() ? "d" : "-") +
                (canExecute() ? "e" : "-") +
                (canRead() ? "r" : "-");
        return access;
    }
}