package Common.Data.Role;

import java.io.Serializable;

public class Analyst extends Roles  implements Serializable {
    public Analyst() {
        super(ROLE.ANALYST, false, false, false, false, true);
    }
}
