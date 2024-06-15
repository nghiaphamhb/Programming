package Server.Utility.Enum;

/**
 * Class for saving query
 */
public interface QUERY {
    //------ROLES-TABLE----------------------------------------------------------------------------------------------------
    String SELECT_ALL_ROLES = "SELECT * FROM " + TABLES.ROLES;

    String SELECT_ROLE_BY_USERNAME =  "SELECT " + TABLES.ROLES + "." + COLUMNS.ROLE +
            " FROM " + TABLES.USERS +
            " JOIN " + TABLES.ROLES +
            " ON " + TABLES.USERS + "." + COLUMNS.ROLE_ID + " = " + TABLES.ROLES + "." + COLUMNS.ID +
            " WHERE " + TABLES.USERS + "." + COLUMNS.USERNAME + " = ?";

    //------USERS-TABLE----------------------------------------------------------------------------------------------------
    String SELECT_USER = "SELECT * FROM " + TABLES.USERS;

    String SELECT_USER_BY_ID = SELECT_USER +
            " WHERE " + COLUMNS.ID + " = ?";

    String SELECT_USER_BY_USERNAME = "SELECT * FROM " + TABLES.USERS +
            " WHERE " + COLUMNS.USERNAME + " = ?";

    String SELECT_USER_BY_USERNAME_AND_PASSWORD = SELECT_USER_BY_USERNAME +
            " AND " + COLUMNS.PASSWORD + " = ?";

    String INSERT_USER = "INSERT INTO " +
            TABLES.USERS + " (" +
            COLUMNS.USERNAME + ", " +
            COLUMNS.PASSWORD + ", " +
            COLUMNS.ROLE_ID + ") VALUES (?, ?, ?)";

    String UPDATE_ROLE_BY_USERNAME = "UPDATE " + TABLES.USERS +
            " SET " + COLUMNS.ROLE_ID + " = ?" +
            " WHERE " + COLUMNS.USERNAME + " =?";

    String UPDATE_ACCESS_BY_ROLE = "UPDATE " + TABLES.ROLES +
            " SET " + COLUMNS.ACCESS + " = ?" +
            " WHERE " + COLUMNS.ROLE + " = ?";

    //------DRAGON-TABLE----------------------------------------------------------------------------------------------------
    String SELECT_ALL_DRAGON = "SELECT * FROM " + TABLES.DRAGON;

    String SELECT_DRAGON_BY_DRAGON_ID = SELECT_ALL_DRAGON +
            " WHERE " + COLUMNS.ID + " = ?";

    String SELECT_COORDINATES_ID_BY_DRAGON_ID = "SELECT " + COLUMNS.COORDINATES_ID +
            " FROM " + TABLES.DRAGON +
            " WHERE " + COLUMNS.ID + " = ?";

    String SELECT_DRAGON_HEAD_ID_BY_DRAGON_ID = "SELECT " + COLUMNS.DRAGON_HEAD_ID
            + " FROM " + TABLES.DRAGON +
            " WHERE " + COLUMNS.ID + " = ?";

    String INSERT_DRAGON = "INSERT INTO " + TABLES.DRAGON + " (" +
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

    String UPDATE_DRAGON_NAME_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON +
            " SET " + COLUMNS.NAME + " = ?" +
            " WHERE " + COLUMNS.ID + " =?";

    String UPDATE_DRAGON_AGE_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON +
            " SET " + COLUMNS.AGE + " = ?" +
            " WHERE " + COLUMNS.ID + " =?";

    String UPDATE_DRAGON_WEIGHT_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON +
            " SET " + COLUMNS.WEIGHT + " = ?" +
            " WHERE " + COLUMNS.ID + " =?";

    String UPDATE_DRAGON_SPEAKING_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON +
            " SET " + COLUMNS.SPEAKING + " = ?" +
            " WHERE " + COLUMNS.ID + " =?";

    String UPDATE_DRAGON_COLOR_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON +
            " SET " + COLUMNS.COLOR + " = ?" +
            " WHERE " + COLUMNS.ID + " =?";

    String DELETE_DRAGON_BY_DRAGON_ID = "DELETE FROM " + TABLES.DRAGON +
            " WHERE " + COLUMNS.ID + " = ?";

//-----COORDINATES-TABLE------------------------------------------------------------------------------------------------
    String SELECT_ALL_COORDINATES = "SELECT * FROM " + TABLES.COORDINATES;

    String SELECT_COORDINATES_BY_COORDINATES_ID = SELECT_ALL_COORDINATES +
            " WHERE " + COLUMNS.ID + " = ?";

    String INSERT_COORDINATES = "INSERT INTO " +
            TABLES.COORDINATES + " (" +
            COLUMNS.X + ", " +
            COLUMNS.Y + ") VALUES (?, ?)";

    String UPDATE_COORDINATES_BY_DRAGON_ID = "UPDATE " + TABLES.COORDINATES +
            " SET " + COLUMNS.X + " = ?, " +
            COLUMNS.Y + " = ?" +
            " FROM " + TABLES.DRAGON +
            " WHERE " + TABLES.COORDINATES + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.COORDINATES_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";

    String DELETE_COORDINATES_BY_DRAGON_ID = "DELETE FROM " + TABLES.COORDINATES +
            " USING " + TABLES.DRAGON +
            " WHERE " + TABLES.COORDINATES + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.COORDINATES_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";

//-------DRAGON-HEAD-TABLE----------------------------------------------------------------------------------------------
    String SELECT_ALL_DRAGON_HEAD = "SELECT * FROM " + TABLES.DRAGON_HEAD;

    String SELECT_DRAGON_HEAD_BY_DRAGON_HEAD_ID = SELECT_ALL_DRAGON_HEAD + " WHERE " +
            COLUMNS.ID + " = ?";

    String INSERT_DRAGON_HEAD = "INSERT INTO " +
            TABLES.DRAGON_HEAD + " (" +
            COLUMNS.EYES_COUNT + ") VALUES (?)";

    String UPDATE_DRAGON_HEAD_BY_DRAGON_ID = "UPDATE " + TABLES.DRAGON_HEAD +
            " SET " + COLUMNS.EYES_COUNT + " = ?" +
            " FROM " + TABLES.DRAGON +
            " WHERE " + TABLES.DRAGON_HEAD + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.DRAGON_HEAD_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";

    String DELETE_DRAGON_HEAD_BY_DRAGON_ID = "DELETE FROM " + TABLES.DRAGON_HEAD +
            " USING " + TABLES.DRAGON +
            " WHERE " + TABLES.DRAGON_HEAD + "." +COLUMNS.ID + " = " + TABLES.DRAGON + "." + COLUMNS.DRAGON_HEAD_ID +
            " AND " + TABLES.DRAGON + "." +COLUMNS.ID + " = ?";
}
