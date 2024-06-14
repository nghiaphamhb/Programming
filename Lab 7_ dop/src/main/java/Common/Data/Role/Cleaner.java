package Common.Data.Role;

import java.io.Serializable;

public class Cleaner extends Roles  implements Serializable {
    public Cleaner() {
        super(ROLE.CLEANER, false, false, true, false, false);
    }
}
