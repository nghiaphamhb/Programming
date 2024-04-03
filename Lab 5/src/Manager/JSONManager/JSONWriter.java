package Manager.JSONManager;

import Data.Dragon;
import Utility.Display;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Записать коллекцию в файл
 */
public class JSONWriter {
    protected final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();;
    protected String fileName;

    public JSONWriter(String fileName) {
        this.fileName = fileName;
        if (!(new File(fileName).exists())) {
            this.fileName = "../" + fileName;
        }
    }
    /**
     * Записать коллекцию в файл
     * @param collection записанная в файл коллекция
     */
    public void write( Collection<Dragon> collection ) {
        try ( PrintWriter collectionPrintWriter = new PrintWriter( new File(fileName) )) {
            collectionPrintWriter.println( gson.toJson(collection) );
            Display.println("Коллекция успешна сохранена в файл!");
        } catch (FileNotFoundException e) {
            Display.printError("Загрузочный файл не может быть открыт!");
        }
    }
}
