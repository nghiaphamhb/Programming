package Server.Manager;

import Common.Data.Dragon;
import Server.Utility.JsonUtility.JSONReader;
import Server.Utility.JsonUtility.JSONWriter;

import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Class for writing and learning content in JSON file
 */
public class FileManager {
    private final JSONWriter jsonWriter;
    private final JSONReader jsonReader;

    public FileManager(String filePath, Logger log) {
        this.jsonWriter = new JSONWriter(filePath, log);
        this.jsonReader = new JSONReader(filePath, log);
    }

    /**
     * Read content from file json
     * @return content in file json
     */
    public Set<Dragon> readFromFile() {
        return jsonReader.readFromFile();
    }

    /**
     * Write content into file json
     * @param collection content into file json
     */
    public void writeIntoFile( Collection<Dragon> collection ){
        jsonWriter.writeIntoFile(collection);
    }


}