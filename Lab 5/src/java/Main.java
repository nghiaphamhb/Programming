import Manager.CommandManager;
import Manager.DragonManager;
import Manager.JSONManager.JSONReader;
import Manager.JSONManager.JSONWriter;
import Utility.Runner;

public class Main {
    public static void main (String [] args){
        CommandManager commandSystem = new CommandManager();
        JSONWriter JSONWriter = new JSONWriter(args[0]);
        JSONReader JSONReader = new JSONReader(args[0]);
        DragonManager collection = new DragonManager(JSONReader, JSONWriter);

        Runner program = new Runner(commandSystem, collection);
        program.start();
    }
}
