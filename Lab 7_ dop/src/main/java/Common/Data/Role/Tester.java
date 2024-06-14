package Common.Data.Role;

import java.io.Serializable;

public class Tester extends Roles  implements Serializable {
    public Tester() {
        super(ROLE.TESTER, false, false, false, true, false);
    }
}
