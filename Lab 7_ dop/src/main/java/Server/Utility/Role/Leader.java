package Server.Utility.Role;

import java.io.Serializable;

public class Leader extends AbstractRole implements Serializable {
    public Leader() {
        super("leader", false, true, false, false, true);
    }
}
