package Common.Data.Role;

import java.io.Serializable;

public class Creator extends Roles  implements Serializable {
    public Creator() {
        super(ROLE.CREATOR, true, false, false, false, false);
    }
}
