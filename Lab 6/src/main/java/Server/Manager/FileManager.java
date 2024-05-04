package Server.Manager;

import Common.Data.Dragon;
import Server.Utility.JsonUtility.JSONReader;
import Server.Utility.JsonUtility.JSONWriter;

import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;

public class FileManager {
    private final JSONWriter jsonWriter;
    private final JSONReader jsonReader;

    public FileManager(String filePath, Logger log) {
        this.jsonWriter = new JSONWriter(filePath, log);
        this.jsonReader = new JSONReader(filePath, log);
    }

    public Set<Dragon> readFromFile() {
        return jsonReader.readFromFile();
    }

    public void writeIntoFile( Collection<Dragon> collection ){
        jsonWriter.writeIntoFile(collection);
    }


}