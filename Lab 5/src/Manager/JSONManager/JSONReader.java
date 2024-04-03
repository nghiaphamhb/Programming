package Manager.JSONManager;

import Data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import Utility.Display;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Читать коллекцию в файле
 */
public class JSONReader {


    protected final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    protected String fileName;

    public JSONReader(String fileName) {
        this.fileName = fileName;
        if (!(new File(fileName).exists())) {
            this.fileName = "../" + fileName;
        }
    }

    /**
     * Получить коллекцию из файла
     * @return полученная из файла коллекция
     */
    public Set<Dragon> read() {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new java.io.FileReader(fileName);
                 var reader = new BufferedReader(fileReader)) {

                StringBuilder jsonString = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonString.append(line.trim());
                }

                if (jsonString.isEmpty()) {
                    jsonString.append("[]");
                }
                Type collectionType = new TypeToken<HashSet<Dragon>>() {}.getType();
                HashSet<Dragon> collection = gson.fromJson(jsonString.toString(), collectionType);


                Display.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                Display.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                Display.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                Display.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IOException exception) {
                Display.printError("Ошибка при чтении файла!");
            }
        } else {
            Display.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new HashSet<>();
    }

}
