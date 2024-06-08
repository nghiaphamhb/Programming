package Server.Utility;


import Client.Utility.Display;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;
import Server.Manager.CollectionManager;
import Server.ServerApp;
import Server.Network.Server;
import Server.Manager.DatabaseCollectionManager;
import Server.Manager.DatabaseUserManager;
import com.sun.javafx.scene.shape.SVGPathHelper;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Class of operation of the program
 */
public class ServerAppRunner implements Runnable{
    private final CommandManager commandManager;
    private final DatabaseUserManager databaseUserManager;
    private final DatabaseCollectionManager databaseCollectionManager;
    private final CollectionManager collectionManager;
    private Server server;
    private boolean serverIsRunning = true;
    private SocketAddress clientAddr = null;
    private Semaphore semaphore;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);


    public ServerAppRunner(int maxClient, CollectionManager collectionManager, int port, DatabaseUserManager databaseUserManager, DatabaseCollectionManager databaseCollectionManager) throws IOException {
        this.collectionManager = collectionManager;
        this.databaseUserManager = databaseUserManager;
        this.databaseCollectionManager = databaseCollectionManager;
        this.commandManager = new CommandManager(collectionManager, databaseUserManager, databaseCollectionManager);
        this.server =  new Server(InetAddress.getLocalHost(), port);
        this.semaphore = new Semaphore(maxClient);
    }

    /**
     * Run the program
     */
    @Override
    public void run() {
        ServerApp.logger.log(Level.INFO, "Server is waiting to connect...");
        while (serverIsRunning) {
//            String messageResponse = acquireConnection();
//            if (!messageResponse.isEmpty()) {
//                Response response = new Response(messageResponse);
//                byte[] data = SerializationUtils.serialize(response);
//                try {
//                    server.sendData(data);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            fixedThreadPool.submit(new ConnectionHandler(server));



//            try {


//            server.disconnectFromClient();

        }
        try {
            fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Stop the program
     */
    private synchronized void stop() {
        fixedThreadPool.shutdown();
        collectionManager.saveCollection();
        server.disconnectFromClient();
        serverIsRunning = false;
        server.clearSocket();
        ServerApp.logger.log(Level.INFO, "Server disconnected from client\nThe program was ended.");
    }


    private String acquireConnection(){
        String message = "";
        try{
            semaphore.acquire();
            ServerApp.logger.log(Level.INFO, "Permission for a new connection has been received.");
        } catch (InterruptedException e) {
            message = "An error occurred while getting permission for a new connection!";
            ServerApp.logger.log(Level.WARNING, "An error occurred while getting permission for a new connection!");
        }
        return message;
    }

    private void releaseConnection() {
        semaphore.release();
        ServerApp.logger.log (Level.INFO, "Connection failure is registered.");
    }


}
