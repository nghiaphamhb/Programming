package Common.Data;

import Common.Data.Role.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * User class
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private Roles role;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.role = null;
    }

    public User(String userName, String password, Roles role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword());
    }

    @Override
    public String toString() {
        return "User: " + getUserName();
    }
}
