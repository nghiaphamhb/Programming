package Main;

import Manager.CommandManager;
import Manager.FileManager.FileManager;
import Manager.FileManager.FileReader;
import Manager.DragonManager;
import Manager.FileManager.FileWriter;
import utility.Runner;

public class Main {
    public static void main (String [] args){
        CommandManager commandSystem = new CommandManager();
        FileManager fileManager = new FileWriter("Data.json");
        DragonManager collection = new DragonManager(fileManager);
        Runner program = new Runner(commandSystem, collection);

        program.start();
    }
}
//execute_script script.txt