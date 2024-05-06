package Server.Utility.JsonUtility;

import Common.Data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Read collection from file json
 */
public class JSONReader {


    protected final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    protected String fileName;
    private final Logger logger;

    public JSONReader(String fileName, Logger log) {
        this.fileName = fileName;
        this.logger = log;
        if (!(new File(fileName).exists())) {
            this.fileName = "src/main/java/" + fileName;
        }
    }

    /**
     * Get collection from json file
     * @return collection
     */
    public Set<Dragon> readFromFile() {
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

                logger.log(Level.INFO, "The collection has been uploaded successfully");
                return collection;

            } catch (FileNotFoundException exception) {
                logger.log(Level.SEVERE, "Could not find that file");
            } catch (NoSuchElementException exception) {
                logger.log(Level.SEVERE, "This file is empty");
            } catch (JsonParseException exception) {
                logger.log(Level.SEVERE, "The required collection was not found in the boot file!");
            } catch (IOException exception) {
                logger.log(Level.SEVERE, "Error while reading this file");
            }
        } else {
            logger.log(Level.SEVERE, "Could not find command's argument in this file");
        }
        return new HashSet<>();
    }

}
