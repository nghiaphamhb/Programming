package Server.Utility.Role;

import java.io.Serializable;

public class Cleaner extends AbstractRole implements Serializable {
    public Cleaner() {
        super("cleaner", false, false, true, false, false);
    }
}
