package Server;

import Server.Manager.Memory.CollectionManager;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.Manager.Database.DatabaseUserManager;
import Server.Utility.DatabaseHandler;
import Server.Utility.ServerAppRunner;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class of Server
 */
public class ServerApp {
    public static Logger logger = Logger.getLogger("ServerLogger");
    private static int port = 1234;
    private static String databaseUsername = "s374806";
    private static String databaseHost = "pg";
    private static String databasePassword = "7FOLYBHhbxLOsdxo";
    private static String databaseAddress = "jdbc:postgresql://" + databaseHost + ":5432/studs";

    public static void main(String[] args){
        try{
            DatabaseHandler databaseHandler = new DatabaseHandler(databaseAddress, databaseUsername, databasePassword);
            DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseHandler);
            DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseUserManager, databaseHandler);
            CollectionManager collectionManager = new CollectionManager(databaseCollectionManager);

            ServerAppRunner app = new ServerAppRunner(collectionManager, port, databaseUserManager, databaseCollectionManager);
            app.run();
        } catch (IOException e) {
            ServerApp.logger.log(Level.WARNING, e.toString());
        }

    }



}
