package Server;

import Server.Manager.CommandManager;
import Server.Manager.DragonCollection;
import Server.Manager.FileManager;
import Server.Network.Server;
import Server.Utility.SvRunner;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SvApp {
    public static final int PORT = 1234;
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args){
        try {
            String filePath = "src/main/java/Server/Data.json";
//            if (args.length == 0) {
//                System.out.println("Введите имя загружаемого файла как аргумент командной строки");
//                System.exit(1);
//            }

            FileManager fileManager = new FileManager(filePath, logger);
            DragonCollection dragonCollection= new DragonCollection(fileManager);
            CommandManager commandManager = new CommandManager();

            Server server = new Server(InetAddress.getLocalHost(), PORT, logger);  //exception
            SvRunner appRunner = new SvRunner(commandManager, dragonCollection, server, logger);

            appRunner.run();
        } catch (SocketException e) {
            logger.log(Level.SEVERE, "A socket error has occurred", e);
        } catch (UnknownHostException e) {
            logger.log(Level.SEVERE, "Unknown host", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
