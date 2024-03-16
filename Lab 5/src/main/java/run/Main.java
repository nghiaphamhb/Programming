package run;

import Manager.CommandManager;
import Manager.DragonManager;
import utility.Console;
import utility.Runner;

import java.io.File;

public class Main {
    public static void main (String [] args){
        CommandManager commandSystem = new CommandManager();
        DragonManager collection = new DragonManager();
        Runner program = new Runner(commandSystem, collection);

        program.start();
    }
}
//execute_script s.txt