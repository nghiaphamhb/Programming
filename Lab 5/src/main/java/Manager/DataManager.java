package Manager;
import Data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import utility.Console;
import utility.LocalDateTimeAdapter;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * This class exchanges data with JSON files
 */
public class DataManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()  //new lines and spaces are added to create a more readable output.
            .serializeNulls()  //Gson will include null value fields in the output JSON string.
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) //register new adapter LocalDateTimeAdapter to perform the conversion of the LocalDateTime object to JSON and vice versa.
            .create();
    private final String fileName;


    public DataManager(String fileName) {
        if ( !(new File(fileName).exists()) ) {
            fileName = "src/main/resources/" + fileName;
        }
        this.fileName = fileName;
    }

    /**
     * Write collection into file
     * @param collection collection will be written
     */
    public void writeCollection( Collection<Dragon> collection ) {
        try ( PrintWriter collectionPrintWriter = new PrintWriter( new File(fileName) )) {
            collectionPrintWriter.println( gson.toJson(collection) );
            Console.println("Коллекция успешна сохранена в файл!");
        } catch (FileNotFoundException e) {
            Console.printError("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Get collection from file
     * @return collection from file
     */
    public Collection<Dragon> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new FileReader(fileName)) {
                var collectionType = new TypeToken<PriorityQueue<Console>>() {}.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }

                PriorityQueue<Dragon> collection = gson.fromJson(jsonString.toString(), collectionType);

                Console.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                Console.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                Console.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                Console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            Console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new PriorityQueue<>();
    }
}
