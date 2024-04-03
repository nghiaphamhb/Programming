package Manager.JSONManager;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сериализовать и десериализовать объекты типа LocalDateTime в и из формата JSON.
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter;

    public LocalDateTimeAdapter() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Sử dụng định dạng ISO_LOCAL_DATE_TIME mặc định
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        String formattedDateTime = src.format(formatter);
        return new JsonPrimitive(formattedDateTime);
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateTimeString = json.getAsString();
        String stringWithoutSpaces = dateTimeString.replaceAll("\\s", "");
        return LocalDateTime.parse(json.getAsString(), formatter);

    }
}
