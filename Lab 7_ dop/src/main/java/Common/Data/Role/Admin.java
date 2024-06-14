package Common.Data.Role;

import java.io.Serializable;

public class Admin extends Roles  implements Serializable {
    public Admin() {
        super(ROLE.ADMIN, true, true, true, true, true);
    }
}
