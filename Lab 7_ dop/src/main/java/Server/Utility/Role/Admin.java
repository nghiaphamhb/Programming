package Server.Utility.Role;

import java.io.Serializable;

public class Admin extends AbstractRole implements Serializable {
    public Admin() {
        super("admin", true, true, true, true, true);
    }
}
