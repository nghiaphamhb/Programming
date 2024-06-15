package Server.Manager.Database;

import Server.ServerApp;
import Server.Utility.DatabaseHandler;
import Server.Utility.Enum.COLUMNS;
import Server.Utility.Enum.QUERY;
import Server.Utility.Enum.ROLES;
import Server.Utility.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class DatabaseRoleManager {
    private Map<Integer, Role> roles = new HashMap<>();

    private DatabaseHandler databaseHandler;

    public DatabaseRoleManager(DatabaseHandler databaseHandler) {
        Role role1 = new Role(1, ROLES.ADMIN, true, true, true, true, true);
        Role role2 = new Role(2, ROLES.CREATOR, true, false, false, false, false);
        Role role3 = new Role(3, ROLES.DEVELOPER, false, true, false, false, false);
        Role role4 = new Role(4, ROLES.CLEANER, false, false, true, false, false);
        Role role5 = new Role(5, ROLES.TESTER, false, false, false, true, false);
        Role role6 = new Role(6, ROLES.ANALYST, false, false, false, false, true);
        int index = 1;
        roles.put(index++, role1);
        roles.put(index++, role2);
        roles.put(index++, role3);
        roles.put(index++, role4);
        roles.put(index++, role5);
        roles.put(index, role6);
        this.databaseHandler = databaseHandler;
    }

    public Role getRoleByNameRole(String nameRole) {
        switch (nameRole){
            case ROLES.ADMIN:
                return roles.get(1);
            case ROLES.CREATOR:
                return roles.get(2);
            case ROLES.DEVELOPER:
                return roles.get(3);
            case ROLES.CLEANER:
                return roles.get(4);
            case ROLES.TESTER:
                return roles.get(5);
            case ROLES.ANALYST:
                return roles.get(6);
            default:
                throw new IllegalArgumentException("Unknown role: " + nameRole);
        }
    }

    public Role getRoleByUsername(String username){
        String nameRole = null;
        Role role = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_ROLE_BY_USERNAME, false);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_ROLE_BY_USERNAME");
            if (resultSet.next()) {
                nameRole = resultSet.getString(COLUMNS.ROLE);
                role = getRoleByNameRole(nameRole);
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_ROLE_BY_USERNAME query!" + QUERY.SELECT_ROLE_BY_USERNAME);
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return role;
    }

    public boolean updateAccessRole(String nameRole, String newAccess){
        PreparedStatement preparedStatement;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(QUERY.UPDATE_ACCESS_BY_ROLE, false);
            preparedStatement.setString(1, newAccess);
            preparedStatement.setString(2, nameRole);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the UPDATE_ACCESS_BY_ROLE query!");
        }
        return false;
    }
}
