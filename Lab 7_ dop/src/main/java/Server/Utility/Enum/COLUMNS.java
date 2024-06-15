package Server.Utility.Enum;

/**
 * Class for saving column's names
 */
public interface COLUMNS {
    // DRAGON table column names
    String ID = "id";
    String NAME = "name";
    String COORDINATES_ID = "coordinates_id";
    String CREATION_DATE = "creation_date";
    String AGE = "age";
    String WEIGHT = "weight";
    String SPEAKING = "speaking";
    String COLOR = "color";
    String DRAGON_HEAD_ID = "dragon_head_id";
    String USER_ID = "user_id";

    // USER_TABLE column names
    String USERNAME = "username";
    String PASSWORD = "password";
    String ROLE_ID = "role_id";

    // ROLES_TABLE column names
    String ROLE = "role";
    String ACCESS = "access";

    // COORDINATES table column names
    String X = "x";
    String Y = "y";

    // DRAGON_HEAD table column names
    String EYES_COUNT = "eyes_count";
}
