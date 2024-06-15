package Server.Manager.Database;

import Server.Utility.Enum.QUERY;
import Server.Utility.Roles.*;
import Common.Data.User;
import Server.ServerApp;
import Server.Utility.Enum.COLUMNS;
import Server.Utility.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * A manager of user database.
 */
public class DatabaseUserManager {
    private DatabaseHandler databaseHandler;

    public DatabaseUserManager(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    /**
     * @param userId User id.
     * @return User by id.
     */
    public User getUserById(long userId) {
        User user = null;
        PreparedStatement preparedSelectUserByIdStatement = null;
        try{
            preparedSelectUserByIdStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_USER_BY_ID, false);
            preparedSelectUserByIdStatement.setLong(1, userId);
            ResultSet resultSet = preparedSelectUserByIdStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER_BY_ID");
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString(COLUMNS.USERNAME),
                        resultSet.getString(COLUMNS.PASSWORD)
                );
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER_BY_ID query!");
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByIdStatement);
        }
        return user;
    }

    public AbstractRole getRoleByUsername(String username){
        String nameRole = null;
        AbstractRole role = null;
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

    public AbstractRole getRoleByNameRole(String nameRole) {
        switch (nameRole){
            case ROLES.ADMIN:
                return new Admin();
            case ROLES.ANALYST:
                return new Analyst();
            case ROLES.CLEANER:
                return new Cleaner();
            case ROLES.CREATOR:
                return new Creator();
            case ROLES.DEVELOPER:
                return new Developer();
            case ROLES.TESTER:
                return new Tester();
            default:
                throw new IllegalArgumentException("Unknown role: " + nameRole);
        }
    }

    public boolean checkUserByUsername(String userName){
        PreparedStatement preparedSelectUserByUsernameAndPasswordStatement = null;
        try{
            preparedSelectUserByUsernameAndPasswordStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_USER_BY_USERNAME, false);
            preparedSelectUserByUsernameAndPasswordStatement.setString(1, userName);
            ResultSet resultSet = preparedSelectUserByUsernameAndPasswordStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER_BY_USERNAME");
            return resultSet.next();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER_BY_USERNAME query!");
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameAndPasswordStatement);
        }
        return false;
    }

    /**
     * Check user by username and password.
     *
     * @param user User.
     * @return Result set.
     */
    public boolean checkUserByUsernameAndPassword(User user){
        PreparedStatement preparedSelectUserByUsernameAndPasswordStatement = null;
        try{
            preparedSelectUserByUsernameAndPasswordStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_USER_BY_USERNAME_AND_PASSWORD, false);
            preparedSelectUserByUsernameAndPasswordStatement.setString(1, user.getUserName());
            preparedSelectUserByUsernameAndPasswordStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedSelectUserByUsernameAndPasswordStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER_BY_USERNAME_AND_PASSWORD");
            return resultSet.next();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER_BY_USERNAME_AND_PASSWORD query!");
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameAndPasswordStatement);
        }
        return false;
    }

    /**
     * Get user id by username.
     *
     * @param user User.
     * @return User id.
     */
    public long getUserIdByUsername(User user){
        Long userId = null;
        PreparedStatement preparedSelectUserByUsernameStatement = null;
        try{
            preparedSelectUserByUsernameStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_USER_BY_USERNAME, false);
            preparedSelectUserByUsernameStatement.setString(1, user.getUserName());
            ResultSet resultSet = preparedSelectUserByUsernameStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER_BY_USERNAME");
            if (resultSet.next()){
                userId = resultSet.getLong(COLUMNS.ID);
            } else userId = -1L;
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER_BY_USERNAME query!");
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameStatement);
        }
        return userId;
    }

    /**
     * Insert user.
     * @param user User.
     * @return Status of insert.
     */

    public boolean insertUser(User user) {
        PreparedStatement preparedInsertUserStatement = null;
        try {
            if (getUserIdByUsername(user) != -1L) return false;
            preparedInsertUserStatement =
                    databaseHandler.getPreparedStatement(QUERY.INSERT_USER, false);
            preparedInsertUserStatement.setString(1, user.getUserName());
            preparedInsertUserStatement.setString(2, user.getPassword());
            preparedInsertUserStatement.setLong(3, 6);
            if (preparedInsertUserStatement.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed INSERT_USER");
            return true;
        } catch (SQLException exception) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the INSERT_USER!");
        } finally {
            databaseHandler.closePreparedStatement(preparedInsertUserStatement);
        }
        return false;
    }

    public Map<String, String> getUserList(){
        Map<String, String> usersList = new HashMap<>();
        PreparedStatement preparedStatement;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(QUERY.SELECT_USER, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER");
            while (resultSet.next()){
                String userName = resultSet.getString(COLUMNS.USERNAME);
                usersList.put(userName, getRoleByUsername(userName).getNameRole() );
            }
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER query!");
        }
        return usersList;
    }

    public boolean changeUserRole(String userName, String nameNewRole){
        PreparedStatement preparedStatement;
        try{
            AbstractRole newRole = getRoleByNameRole(nameNewRole);
            preparedStatement = databaseHandler.getPreparedStatement(QUERY.UPDATE_ROLE_BY_USERNAME, false);
            preparedStatement.setLong(1, newRole.getId());
            preparedStatement.setString(2, userName);
            int affectedRows = preparedStatement.executeUpdate();
            return (affectedRows > 0);
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the UPDATE_ROLE_AND_ACCESS_BY_USERNAME query!");
        }
        return false;
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
