package Server.Utility.JsonUtility;

import Common.Data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write updated collection into json file
 */
public class JSONWriter {
    protected final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();;
    protected String fileName;
    private final Logger logger;

    public JSONWriter(String fileName, Logger log) {
        this.fileName = fileName;
        this.logger = log;
        if (!(new File(fileName).exists())) {
            this.fileName = "src/main/java/" + fileName;
        }
    }
    /**
     * Write collection into file
     * @param collection updated collection
     */
    public void writeIntoFile( Collection<Dragon> collection ) {
        try ( PrintWriter collectionPrintWriter = new PrintWriter( new File(fileName) )) {
            collectionPrintWriter.println( gson.toJson(collection) );
            logger.log(Level.INFO, "Collection was successfully saved in file");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Could not open this file");
        }
    }
}
