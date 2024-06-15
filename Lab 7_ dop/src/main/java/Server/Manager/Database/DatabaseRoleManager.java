package Server.Manager.Database;

import Server.ServerApp;
import Server.Utility.DatabaseHandler;
import Server.Utility.Enum.COLUMNS;
import Server.Utility.Enum.QUERY;
import Server.Utility.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Class management role data in the database
 */
public class DatabaseRoleManager {
    private final Map<Long, Role> roles;
    private DatabaseHandler databaseHandler;

    public DatabaseRoleManager(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        roles = getRoles();
    }

    /**
     * Set up access of role
     * @param strAccess string of access
     * @param role role
     */
    private void setAccess(String strAccess, Role role){
        String[] elementStrAccess = strAccess.split("");

        if (containsCharacter(elementStrAccess, "c")) role.setCreate(true);
        if (containsCharacter(elementStrAccess, "u")) role.setUpdate(true);
        if (containsCharacter(elementStrAccess, "d")) role.setDelete(true);
        if (containsCharacter(elementStrAccess, "e")) role.setExecute(true);
        if (containsCharacter(elementStrAccess, "r")) role.setRead(true);
    }

    /**
     * Check if in the array contains character
     * @param array array
     * @param character character
     * @return boolean
     */
    private boolean containsCharacter(String[] array, String character) {
        for (String s : array) {
            if (s.equals(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get role datas from Database
     * @return role map
     */
    public Map<Long, Role> getRoles(){
        Map<Long, Role> roles = new HashMap<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_ALL_ROLES, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(resultSet.getString(COLUMNS.ROLE), false, false, false, false,
                        false);
                setAccess(resultSet.getString(COLUMNS.ACCESS), role);
                roles.put(resultSet.getLong(COLUMNS.ID), role);
            }
            ServerApp.logger.log(Level.INFO, "Completed download roles..");
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "Download failed roles.");
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return roles;
    }

    /**
     * Get role id by role
     * @param role role
     * @return role id
     */
    public Long getRoleIdByRole(Role role){
        for (Map.Entry<Long, Role> entry : roles.entrySet()) {
                if (entry.getValue().equals(role)) {
                    return entry.getKey();
                }
            }
            return null;
    }

    /**
     * Get role by role name
     * @param nameRole role name
     * @return role
     */
    public Role getRoleByNameRole(String nameRole) {
        for (Map.Entry<Long, Role> entry : roles.entrySet()) {
            if (entry.getValue().getNameRole().equals(nameRole)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Get role by username
     * @param username user name
     * @return his role
     */
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

    /**
     * Update role access
     * @param nameRole role name
     * @param newAccess new role access
     * @return true if done
     */
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
