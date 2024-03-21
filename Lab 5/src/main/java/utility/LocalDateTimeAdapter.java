package utility;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * New adapter for conversion from LocalDateTime to JSON and vice versa
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  
  @Override
  public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
  }

  @Override
  public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
  }
}
