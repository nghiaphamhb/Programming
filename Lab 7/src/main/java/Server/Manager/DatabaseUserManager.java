package Server.Manager;

import Client.Utility.Display;
import Common.Data.User;
import Server.ServerApp;
import Server.Utility.Database.COLUMNS;
import Server.Utility.Database.DatabaseHandler;
import Server.Utility.Database.TABLES;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

//quan li cac user trong database
public class DatabaseUserManager {
    // USER_TABLE
    private final String SELECT_USER_BY_ID = "SELECT * FROM " + TABLES.USER +
            " WHERE " + COLUMNS.ID + " = ?";
    private final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + TABLES.USER +
            " WHERE " + COLUMNS.USERNAME + " = ?";
    private final String SELECT_USER_BY_USERNAME_AND_PASSWORD = SELECT_USER_BY_USERNAME + " AND " +
            COLUMNS.PASSWORD + " = ?";
    private final String INSERT_USER = "INSERT INTO " +
            TABLES.USER + " (" +
            COLUMNS.USERNAME + ", " +
            COLUMNS.PASSWORD + ") VALUES (?, ?)";

    private DatabaseHandler databaseHandler;

    public DatabaseUserManager(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public User getUserById(long userId) {
        User user = null;
        PreparedStatement preparedSelectUserByIdStatement = null;
        try{
            preparedSelectUserByIdStatement = databaseHandler.getPreparedStatement(SELECT_USER_BY_ID, false);
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

    public boolean checkUserByUsernameAndPassword(User user){
        PreparedStatement preparedSelectUserByUsernameAndPasswprdStatement = null;
        try{
            preparedSelectUserByUsernameAndPasswprdStatement = databaseHandler.getPreparedStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD, false);
            preparedSelectUserByUsernameAndPasswprdStatement.setString(1, user.getUserName());
            preparedSelectUserByUsernameAndPasswprdStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedSelectUserByUsernameAndPasswprdStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_USER_BY_USERNAME_AND_PASSWORD");
            return resultSet.next();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the SELECT_USER_BY_USERNAME_AND_PASSWORD query!");
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameAndPasswprdStatement);
        }
        return false;
    }

    public long getUserIdByUser(User user){
        Long userId = null;
        PreparedStatement preparedSelectUserByUsernameStatement = null;
        try{
            preparedSelectUserByUsernameStatement = databaseHandler.getPreparedStatement(SELECT_USER_BY_USERNAME, false);
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

    public boolean insertUser(User user) {
        PreparedStatement preparedInsertUserStatement = null;
        try {
            if (getUserIdByUser(user) != -1L) return false;
            preparedInsertUserStatement =
                    databaseHandler.getPreparedStatement(INSERT_USER, false);
            preparedInsertUserStatement.setString(1, user.getUserName());
            preparedInsertUserStatement.setString(2, user.getPassword());
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
}
