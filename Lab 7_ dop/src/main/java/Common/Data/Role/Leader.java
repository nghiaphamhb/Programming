package Common.Data.Role;

import java.io.Serializable;

public class Leader extends Roles  implements Serializable {
    public Leader() {
        super(ROLE.LEADER, false, true, false, false, true);
    }
}
