package Server.Utility.JsonUtility;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serialize and deserialize LocalDateTime objects to and from JSON format.
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter;

    public LocalDateTimeAdapter() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    /**
     * Serialize LocalDateTime objects to JSON format.
     * @param src LocalDateTime object
     * @param typeOfSrc LocalDateTime type
     * @param context Serialization context
     * @return JsonElement representing the serialized LocalDateTime
     */
    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        String formattedDateTime = src.format(formatter);
        return new JsonPrimitive(formattedDateTime);
    }

    /**
     * Deserialize a JSON element into a LocalDateTime object.
     * @param json The JSON element representing a LocalDateTime as a string
     * @param typeOfT The type of the object to deserialize to (LocalDateTime in this case)
     * @param context Deserialization context
     * @return The deserialized LocalDateTime object
     * @throws JsonParseException If an error occurs during deserialization
     */
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateTimeString = json.getAsString();
        String stringWithoutSpaces = dateTimeString.replaceAll("\\s", "");
        return LocalDateTime.parse(json.getAsString(), formatter);

    }
}
