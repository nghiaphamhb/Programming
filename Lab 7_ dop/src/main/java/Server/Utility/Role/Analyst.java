package Server.Utility.Role;

import java.io.Serializable;

public class Analyst extends AbstractRole implements Serializable {
    public Analyst() {
        super("analyst", false, false, false, false, true);
    }
}
