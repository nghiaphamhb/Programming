package Server.Utility.Role;

import java.io.Serializable;

public class Tester extends AbstractRole implements Serializable {
    public Tester() {
        super("tester", false, false, false, true, false);
    }
}
