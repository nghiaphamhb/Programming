package Server.Utility.Role;

import java.io.Serializable;

public class Creator extends AbstractRole implements Serializable {
    public Creator() {
        super("creator", true, false, false, false, false);
    }
}
