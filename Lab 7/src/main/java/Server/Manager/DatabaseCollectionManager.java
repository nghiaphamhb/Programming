package Server.Manager;

import Client.Utility.Display;
import Common.Data.Dragon.Color;
import Common.Data.Dragon.Coordinates;
import Common.Data.Dragon.Dragon;
import Common.Data.Dragon.DragonHead;
import Common.Data.User;
import Server.ServerApp;
import Server.Utility.Database.COLUMNS;
import Server.Utility.Database.DML;
import Server.Utility.Database.DatabaseHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.logging.Level;

//Interact(management) with collections in the database
public class DatabaseCollectionManager {
    private Server.Manager.DatabaseUserManager DatabaseUserManager;
    private DatabaseHandler databaseHandler;

    public DatabaseCollectionManager(DatabaseUserManager DatabaseUserManager, DatabaseHandler databaseHandler) {
        this.DatabaseUserManager = DatabaseUserManager;
        this.databaseHandler = databaseHandler;
    }

    /**
     * @param newDragon New dragon
     * @param user User.
     * @return Marine.
     */
    public boolean insertDragon(Dragon newDragon, User user){
        PreparedStatement dragonTablePreparedStatement = null;
        PreparedStatement coordinatesTablePreparedStatement = null;
        PreparedStatement dragonHeadTablePreparedStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();
            LocalDateTime creationDate = LocalDateTime.now();

            dragonTablePreparedStatement = databaseHandler.getPreparedStatement(DML.INSERT_DRAGON, true);
            coordinatesTablePreparedStatement = databaseHandler.getPreparedStatement(DML.INSERT_COORDINATES, true);
            dragonHeadTablePreparedStatement = databaseHandler.getPreparedStatement(DML.INSERT_DRAGON_HEAD, true);

            //coordinates table
            coordinatesTablePreparedStatement.setInt(1, newDragon.getCoordinates().getX());
            coordinatesTablePreparedStatement.setFloat(2, newDragon.getCoordinates().getY());
            if (coordinatesTablePreparedStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedCoordinatesKeys = coordinatesTablePreparedStatement.getGeneratedKeys();
            long coordinatesId;
            if (generatedCoordinatesKeys.next()) {
                coordinatesId = generatedCoordinatesKeys.getLong(1);
            } else throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed INSERT_COORDINATES.");

            //dragon head table
            dragonHeadTablePreparedStatement.setNull(1, Types.INTEGER);
            if (newDragon.getHead().getEyesCount() != -1L) dragonHeadTablePreparedStatement.setLong(1, newDragon.getHead().getEyesCount());
            if (dragonHeadTablePreparedStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedDragonHeadKeys = dragonHeadTablePreparedStatement.getGeneratedKeys();
            long dragonHeadId;
            if (generatedDragonHeadKeys.next()) {
                dragonHeadId = generatedDragonHeadKeys.getLong(1);
            } else throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed INSERT_DRAGON_HEAD.");

            //dragon table
            dragonTablePreparedStatement.setString(1, newDragon.getName());
            dragonTablePreparedStatement.setLong(2, coordinatesId);
            dragonTablePreparedStatement.setTimestamp(3, Timestamp.valueOf(creationDate)); //phải đưa về dạng TimeStamp trong SQL
            dragonTablePreparedStatement.setInt(4, newDragon.getAge());

            dragonTablePreparedStatement.setNull(5, Types.BIGINT);
            if (newDragon.getWeight() != null) dragonTablePreparedStatement.setLong(5, newDragon.getWeight());

            dragonTablePreparedStatement.setBoolean(6, newDragon.getSpeaking());

            dragonTablePreparedStatement.setNull(7, Types.VARCHAR);
            if (newDragon.getColor() != null) dragonTablePreparedStatement.setString(7, newDragon.getColor().toString());

            dragonTablePreparedStatement.setLong(8, dragonHeadId);
            dragonTablePreparedStatement.setLong(9, DatabaseUserManager.getUserIdByUser(user));

            if (dragonTablePreparedStatement.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed INSERT_DRAGON.");

            databaseHandler.commit();
            return true;

        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, e.toString());
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing a group of requests to add a new object!");
        } finally {
            databaseHandler.closePreparedStatement(dragonTablePreparedStatement);
            databaseHandler.closePreparedStatement(dragonHeadTablePreparedStatement);
            databaseHandler.closePreparedStatement(coordinatesTablePreparedStatement);
            databaseHandler.setNormalMode();
        }
        return false;
    }

    public void updateDragonById(long dragonId, Dragon updatedDragon){
        PreparedStatement pstUpdateName = null;
        PreparedStatement pstUpdateCoordinates = null;
        PreparedStatement pstUpdateAge = null;
        PreparedStatement pstUpdateWeight = null;
        PreparedStatement pstUpdateSpeaking = null;
        PreparedStatement pstUpdateColor = null;
        PreparedStatement pstUpdateDragonHead = null;

        try{
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();

            pstUpdateName = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_NAME_BY_DRAGON_ID, false);
            pstUpdateCoordinates = databaseHandler.getPreparedStatement(DML.UPDATE_COORDINATES_BY_DRAGON_ID, false);
            pstUpdateAge = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_AGE_BY_DRAGON_ID, false);
            pstUpdateWeight = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_WEIGHT_BY_DRAGON_ID, false);
            pstUpdateSpeaking = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_SPEAKING_BY_DRAGON_ID, false);
            pstUpdateColor = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_COLOR_BY_DRAGON_ID, false);
            pstUpdateDragonHead = databaseHandler.getPreparedStatement(DML.UPDATE_DRAGON_HEAD_BY_DRAGON_ID, false);

            if(updatedDragon.getName() != null){
                pstUpdateName.setString(1, updatedDragon.getName());
                pstUpdateName.setLong(2, dragonId);
                if (pstUpdateName.executeUpdate() == 0) throw new SQLException();
                ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_NAME_BY_ID.");
            }

            if(updatedDragon.getCoordinates() != null){
                pstUpdateCoordinates.setInt(1, updatedDragon.getCoordinates().getX());
                pstUpdateCoordinates.setLong(2, updatedDragon.getCoordinates().getY());
                pstUpdateCoordinates.setLong(3, dragonId);
                if (pstUpdateCoordinates.executeUpdate() == 0) throw new SQLException();
                ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_COORDINATES_BY_ID.");
            }

            pstUpdateAge.setInt(1, updatedDragon.getAge());
            pstUpdateAge.setLong(2, dragonId);
            if (pstUpdateAge.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_AGE_BY_ID.");

            if(updatedDragon.getWeight() != null){
                pstUpdateWeight.setLong(1, updatedDragon.getWeight());
            } else {
                pstUpdateWeight.setNull(1, Types.BIGINT);
            }
            pstUpdateWeight.setLong(2, dragonId);
            if (pstUpdateWeight.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_WEIGHT_BY_ID.");

            pstUpdateSpeaking.setString(1, updatedDragon.getSpeaking().toString());
            pstUpdateSpeaking.setLong(2, dragonId);
            if (pstUpdateSpeaking.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_SPEAKING_BY_ID.");

            if(updatedDragon.getColor() != null){
                pstUpdateColor.setString(1, updatedDragon.getColor().toString());
            } else {
                pstUpdateColor.setNull(1, Types.VARCHAR);
            };
            pstUpdateColor.setLong(2, dragonId);
            if (pstUpdateColor.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_COLOR_BY_ID.");

            if(updatedDragon.getHead() != null){
                pstUpdateDragonHead.setLong(1, updatedDragon.getHead().getEyesCount());
                pstUpdateDragonHead.setLong(2, dragonId);
                if (pstUpdateDragonHead.executeUpdate() == 0) throw new SQLException();
                ServerApp.logger.log(Level.INFO, "Executed UPDATE_DRAGON_HEAD_BY_ID.");
            }

            databaseHandler.commit();

        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing a group of object update requests!");
            Display.printError(e);
            databaseHandler.rollback();
        } finally {
            databaseHandler.closePreparedStatement(pstUpdateName);
            databaseHandler.closePreparedStatement(pstUpdateCoordinates);
            databaseHandler.closePreparedStatement(pstUpdateAge);
            databaseHandler.closePreparedStatement(pstUpdateWeight);
            databaseHandler.closePreparedStatement(pstUpdateSpeaking);
            databaseHandler.closePreparedStatement(pstUpdateColor);
            databaseHandler.closePreparedStatement(pstUpdateDragonHead);
            databaseHandler.setNormalMode();
        }
    }

    public void deleteDragonById(long dragonId){
        PreparedStatement pstDeleteDragonById = null;
        try{
            pstDeleteDragonById = databaseHandler.getPreparedStatement(DML.DELETE_DRAGON_BY_DRAGON_ID, false);
            pstDeleteDragonById.setLong(1, dragonId);
            if (pstDeleteDragonById.executeUpdate() == 0) throw new SQLException();
            ServerApp.logger.log(Level.INFO, "Executed DELETE_DRAGON_BY_DRAGON_ID.");
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the DELETE_DRAGON_BY_DRAGON_ID request!");
        } finally {
            databaseHandler.closePreparedStatement(pstDeleteDragonById);
        }
    }

    public HashSet<Dragon> getCollection(){
        HashSet<Dragon> dragonsCollection = new HashSet<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getPreparedStatement(DML.SELECT_ALL_DRAGON, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dragonsCollection.add(createDragon(resultSet));
            }
//        } catch (SQLException e) {
//            ServerApp.logger.log(Level.WARNING, "Download failed collection due to empty database.");
        } catch (Exception e) {
            ServerApp.logger.log(Level.WARNING, e.toString());
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return dragonsCollection;
    }

    /**
     * Clear the collection.
     */
    public void clearCollection(){
        HashSet<Dragon> dragonsCollection = getCollection();
        for (Dragon dragon : dragonsCollection){
            deleteDragonById(dragon.getId());
        }
    }

//    public boolean check

    //----------------Auxiliary functions for the main functions------------------------------(Private)-----------------
    /** @param resultSet Result set parameters of Dragon.
     * @return New Dragon.
     * @throws SQLException When there's exception inside.
     */
    private Dragon createDragon(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMNS.ID);
        String name = resultSet.getString(COLUMNS.NAME);
        Coordinates coordinates = getCoordinatesByDragonId(id);
        LocalDateTime creationDate= resultSet.getTimestamp(COLUMNS.CREATION_DATE).toLocalDateTime();
        int age = resultSet.getInt(COLUMNS.AGE);
        Long weight = resultSet.getLong(COLUMNS.WEIGHT);  //can be null
        boolean speaking = resultSet.getBoolean(COLUMNS.SPEAKING);

        String strColor = resultSet.getString(COLUMNS.COLOR);
        Color color = null;
        if (strColor != null) color = Color.valueOf(strColor);   // can be null

        DragonHead dragonHead = getDragonHeadByDragonId(id);  //eyes count can be null
        User user = DatabaseUserManager.getUserById(resultSet.getLong(COLUMNS.USER_ID));

        return new Dragon(id, name, coordinates, creationDate, age, weight, speaking, color, dragonHead, user);
    }
    private Long getCoordinatesIdByDragonId(long dragonId){
        Long coordinates_id = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(DML.SELECT_COORDINATES_ID_BY_DRAGON_ID, false);
            preparedStatement.setLong(1, dragonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_COORDINATES_ID_BY_DRAGON_ID");
            if (resultSet.next()){
                coordinates_id = resultSet.getLong(COLUMNS.COORDINATES_ID);
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the query SELECT_COORDINATES_ID_BY_DRAGON_ID!");
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return coordinates_id;
    }

    private Coordinates getCoordinatesByDragonId(long dragonId){
        Coordinates coordinates = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(DML.SELECT_COORDINATES_BY_COORDINATES_ID, false);
            long coordinatesId = getCoordinatesIdByDragonId(dragonId);
            preparedStatement.setLong(1, coordinatesId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_COORDINATES_BY_DRAGON_ID");
            if (resultSet.next()){
                coordinates = new Coordinates(
                    resultSet.getInt(COLUMNS.X),
                        resultSet.getLong(COLUMNS.Y)
                );
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the query SELECT_COORDINATES_BY_DRAGON_ID!");
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return coordinates;
    }

    private Long getDragonHeadIdByDragonId(long dragonId){
        Long dragon_head_id = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(DML.SELECT_DRAGON_HEAD_ID_BY_DRAGON_ID, false);
            preparedStatement.setLong(1, dragonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_DRAGON_HEAD_ID_BY_DRAGON_ID");
            if (resultSet.next()){
                dragon_head_id = resultSet.getLong(COLUMNS.DRAGON_HEAD_ID);
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the query SELECT_DRAGON_HEAD_ID_BY_DRAGON_ID!");
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return dragon_head_id;
    }

    private DragonHead getDragonHeadByDragonId(long dragonId){
        DragonHead dragonHead = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseHandler.getPreparedStatement(DML.SELECT_DRAGON_HEAD_BY_DRAGON_HEAD_ID, false);
            long dragonHeadId = getDragonHeadIdByDragonId(dragonId);
            preparedStatement.setLong(1, dragonHeadId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ServerApp.logger.log(Level.INFO, "Executed query SELECT_DRAGON_HEAD_BY_DRAGON_HEAD_ID");
            if (resultSet.next()){
                Long eyes_count = resultSet.getLong(COLUMNS.EYES_COUNT);
                dragonHead = new DragonHead();
                if (eyes_count != null) dragonHead.setEyesCount(eyes_count);
            } else throw new SQLException();
        } catch (SQLException e) {
            ServerApp.logger.log(Level.WARNING, "An error occurred while executing the query SELECT_DRAGON_HEAD_BY_DRAGON_HEAD_ID!");
        } finally {
            databaseHandler.closePreparedStatement(preparedStatement);
        }
        return dragonHead;
    }

}
