package Main;

import Manager.CommandManager;
import Manager.DataManager;
import Manager.DragonManager;
import utility.Runner;

public class Main {
    public static void main (String [] args){
        CommandManager commandSystem = new CommandManager();
        DataManager dataManager = new DataManager("Data.json");
        DragonManager collection = new DragonManager(dataManager);
        Runner program = new Runner(commandSystem, collection);

        program.start();
    }
}
//execute_script script.txt