package Server;

import Server.Manager.CommandManager;
import Server.Manager.CollectionManager;
import Server.Network.Server;
import Server.Manager.DatabaseCollectionManager;
import Server.Manager.DatabaseUserManager;
import Server.Utility.Database.DatabaseHandler;
import Server.Utility.ServerAppRunner;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

/**
 * Main class of Server
 */
public class ServerApp {
    public static Logger logger = Logger.getLogger("ServerLogger");
    private static int port = 1234;
    private static String databaseUsername = "s374806";
    private static String databaseHost = "localhost";
    private static String databasePassword = "7FOLYBHhbxLOsdxo";
    private static String databaseAddress = "jdbc:postgresql://" + databaseHost + ":8080/studs";;

    public static void main(String[] args){
        try{
            DatabaseHandler databaseHandler = new DatabaseHandler(databaseAddress, databaseUsername, databasePassword);
            DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseHandler);
            DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseUserManager, databaseHandler);
            CollectionManager collectionManager = new CollectionManager(databaseCollectionManager);
            CommandManager commandManager = new CommandManager(collectionManager, databaseUserManager, databaseCollectionManager);

            ServerAppRunner app = new ServerAppRunner(10, collectionManager, port, databaseUserManager, databaseCollectionManager);
            app.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
