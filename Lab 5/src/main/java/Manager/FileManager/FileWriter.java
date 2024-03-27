package Manager.FileManager;

import Data.Dragon;
import utility.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
/**
 * Writes into JSON files
 */
public class FileWriter extends FileManager{
    public FileWriter(String fileName) {
        super(fileName);
    }

    /**
     * Write collection into file
     * @param collection collection will be written
     */
    @Override
    public void write( Collection<Dragon> collection ) {
        try ( PrintWriter collectionPrintWriter = new PrintWriter( new File(fileName) )) {
            collectionPrintWriter.println( gson.toJson(collection) );
            Console.println("Коллекция успешна сохранена в файл!");
        } catch (FileNotFoundException e) {
            Console.printError("Загрузочный файл не может быть открыт!");
        }
    }
}
