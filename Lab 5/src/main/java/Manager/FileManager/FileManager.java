package Manager.FileManager;

import Data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utility.LocalDateTimeAdapter;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;

public abstract class FileManager {
    protected final Gson gson;
    protected String fileName;

    public FileManager(String fileName) {
        if (!(new File(fileName).exists())) {
            this.fileName = "src/main/resources/" + fileName;
        }
        gson = new GsonBuilder()
                .setPrettyPrinting()  //new lines and spaces are added to create a more readable output.
                .serializeNulls()  //Gson will include null value fields in the output JSON string.
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) //register new adapter LocalDateTimeAdapter to perform the conversion of the LocalDateTime object to JSON and vice versa.
                .create();
    }
    public Collection<Dragon> read(){
        return null;
    };
    public void write( Collection<Dragon> collection ){};
}

