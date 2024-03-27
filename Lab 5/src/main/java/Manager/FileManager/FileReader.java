package Manager.FileManager;
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
 * Reads JSON files
 */
public class FileReader extends FileManager{


    public FileReader(String fileName) {
        super(fileName);
    }

    /**
     * Get collection from file
     * @return collection from file
     */
    @Override
    public Collection<Dragon> read() {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new java.io.FileReader(fileName)) {
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
