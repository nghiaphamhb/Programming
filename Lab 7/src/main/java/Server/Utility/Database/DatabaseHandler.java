package Server.Utility.Database;

import Server.ServerApp;

import java.sql.*;
import java.util.logging.Level;

// van hanh dbms
public class DatabaseHandler {

    private final String JDBC_DRIVER = "org.postgresql.Driver";

    private String url;
    private String userName;
    private String password;
    private Connection connection;

    public DatabaseHandler(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        connectToDataBase();
    }

    private void connectToDataBase(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, userName, password);
            ServerApp.logger.log(Level.INFO, "Connect to DB successfully!");
        } catch (ClassNotFoundException e) {
            ServerApp.logger.log(Level.WARNING, "The database management driver was not found!");
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while connecting to the database!");
        }
    }

    public void disconnectToDataBase(){
        if (connection == null) return;
        try{
            connection.close();
            ServerApp.logger.log(Level.INFO, "Disconnect to DB successfully!");
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while disconnecting to the database!");
        }
    }

    public PreparedStatement getPreparedStatement(String sqlStatement, boolean generateKeys){
        PreparedStatement preparedStatement;
        try{
            if (connection == null) throw new SQLException();
            int autoGenerateKeys = generateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
            preparedStatement = connection.prepareStatement(sqlStatement, autoGenerateKeys);
            return preparedStatement;
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while connecting to the database!");
        }
        return null;
    }

    public void closePreparedStatement(PreparedStatement sqlStatement){
        if (sqlStatement == null) return;
        try{
            sqlStatement.close();
        } catch (SQLException e){
        }
    }

    public void setCommitMode(){
        try{
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while setting the database transaction mode!");
        }
    }

    public void setNormalMode() {
        try {
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while setting the normal database mode!");
        }
    }

    public void commit() {
        try {
            if (connection == null) throw new SQLException();
            connection.commit();
        } catch (SQLException exception) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while confirming the new state of the database!");
        }
    }

    public void rollback() {
        try {
            if (connection == null) throw new SQLException();
            connection.rollback();
        } catch (SQLException exception) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while returning the initial state of the database!");
        }
    }


    public void setSavepoint() {
        try {
            if (connection == null) throw new SQLException();
            connection.setSavepoint();
        } catch (SQLException exception) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while saving the database state!");
        }
    }
}
