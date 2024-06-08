package Server.Utility.Database;

public interface DML {
//------DRAGON-TABLE----------------------------------------------------------------------------------------------------
    static final String SELECT_ALL_DRAGON = "SELECT * FROM " + TABLES.DRAGON;
    static final String SELECT_DRAGON_BY_DRAGON_ID = SELECT_ALL_DRAGON + " WHERE " +
            COLUMNS.ID + " = ?";
    static final String SELECT_COORDINATES_ID_BY_DRAGON_ID = "SELECT " + COLUMNS.COORDINATES_ID
            + " FROM " + TABLES.DRAGON + " WHERE " + COLUMNS.ID + " = ?";
    static final String SELECT_DRAGON_HEAD_ID_BY_DRAGON_ID = "SELECT " + COLUMNS.DRAGON_HEAD_ID
            + " FROM " + TABLES.DRAGON + " WHERE " + COLUMNS.ID + " = ?";
    static final String INSERT_DRAGON = "INSERT INTO " + TABLES.DRAGON + " (" +
            COLUMNS.NAME + ", " +
            COLUMNS.COORDINATES_ID + ", " +
            COLUMNS.CREATION_DATE + ", " +
            COLUMNS.AGE + ", " +
            COLUMNS.WEIGHT + ", " +
            COLUMNS.SPEAKING + ", " +
            COLUMNS.COLOR + ", " +
            COLUMNS.DRAGON_HEAD_ID + ", " +
            COLUMNS.USER_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?," +
            "?, ?)";
    static final String UPDATE_DRAGON_NAME_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON + " SET " +
            COLUMNS.NAME + " = ?" + " WHERE " +
            COLUMNS.ID + " =?";
    static final String UPDATE_DRAGON_AGE_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON + " SET " +
            COLUMNS.AGE + " = ?" + " WHERE " +
            COLUMNS.ID + " =?";
    static final String UPDATE_DRAGON_WEIGHT_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON + " SET " +
            COLUMNS.WEIGHT + " = ?" + " WHERE " +
            COLUMNS.ID + " =?";
    static final String UPDATE_DRAGON_SPEAKING_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON + " SET " +
            COLUMNS.SPEAKING + " = ?" + " WHERE " +
            COLUMNS.ID + " =?";
    static final String UPDATE_DRAGON_COLOR_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON + " SET " +
            COLUMNS.COLOR + " = ?" + " WHERE " +
            COLUMNS.ID + " =?";
    static final String DELETE_DRAGON_BY_DRAGON_ID = "DELETE FROM " + TABLES.DRAGON + " WHERE " +
            COLUMNS.ID + " = ?";

//-----COORDINATES-TABLE------------------------------------------------------------------------------------------------
    static final String SELECT_ALL_COORDINATES = "SELECT * FROM " + TABLES.COORDINATES;
    static final String SELECT_COORDINATES_BY_COORDINATES_ID = SELECT_ALL_COORDINATES + " WHERE " +
            COLUMNS.ID + " = ?";
    static final String INSERT_COORDINATES = "INSERT INTO " +
            TABLES.COORDINATES + " (" +
            COLUMNS.X + ", " +
            COLUMNS.Y + ") VALUES (?, ?)";
    static final String UPDATE_COORDINATES_BY_DRAGON_ID = "UPDATE " + TABLES.COORDINATES + " SET " +
            COLUMNS.X + " = ?, " +
            COLUMNS.Y + " = ?" +
            " FROM " + TABLES.DRAGON +
            " WHERE " + TABLES.COORDINATES + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.COORDINATES_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";
    static final String DELETE_COORDINATES_BY_DRAGON_ID = "DELETE FROM " + TABLES.COORDINATES +
            " USING " + TABLES.DRAGON +
            " WHERE " + TABLES.COORDINATES + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.COORDINATES_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";
//-------DRAGON-HEAD-TABLE----------------------------------------------------------------------------------------------
    static final String SELECT_ALL_DRAGON_HEAD = "SELECT * FROM " + TABLES.DRAGON_HEAD;
    static final String SELECT_DRAGON_HEAD_BY_DRAGON_HEAD_ID = SELECT_ALL_DRAGON_HEAD + " WHERE " +
            COLUMNS.ID + " = ?";
    static final String INSERT_DRAGON_HEAD = "INSERT INTO " +
            TABLES.DRAGON_HEAD + " (" +
            COLUMNS.EYES_COUNT + ") VALUES (?)";
    static final String UPDATE_DRAGON_HEAD_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON_HEAD +
            " SET " + COLUMNS.EYES_COUNT + " = ?" +
            " FROM " + TABLES.DRAGON +
            " WHERE " + TABLES.DRAGON_HEAD + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.DRAGON_HEAD_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";
    static final String DELETE_DRAGON_HEAD_BY_DRAGON_ID = "DELETE FROM " + TABLES.DRAGON_HEAD +
            " USING " + TABLES.DRAGON +
            " WHERE " + TABLES.DRAGON_HEAD + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.DRAGON_HEAD_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";
}
