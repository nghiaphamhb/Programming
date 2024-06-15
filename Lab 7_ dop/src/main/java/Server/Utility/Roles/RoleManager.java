package Server.Utility.Roles;

public class RoleManager {
    private Role role1;
    private Role role2;
    private Role role3;
    private Role role4;
    private Role role5;
    private Role role6;

    public RoleManager() {
        role1 = new Role(1, ROLES.ADMIN, true, true, true, true, true);
        role2 = new Role(2, ROLES.CREATOR, true, false, false, false, false);
        role3 = new Role(3, ROLES.DEVELOPER, false, true, false, false, false);
        role4 = new Role(4, ROLES.CLEANER, false, false, true, false, false);
        role5 = new Role(5, ROLES.TESTER, false, false, false, true, false);
        role6 = new Role(6, ROLES.ANALYST, false, false, false, false, true);
    }

    public Role getRoleByNameRole(String nameRole) {
        switch (nameRole){
            case ROLES.ADMIN:
                return role1;
            case ROLES.ANALYST:
                return role6;
            case ROLES.CLEANER:
                return role4;
            case ROLES.CREATOR:
                return role2;
            case ROLES.DEVELOPER:
                return role3;
            case ROLES.TESTER:
                return role5;
            default:
                throw new IllegalArgumentException("Unknown role: " + nameRole);
        }
    }

}
