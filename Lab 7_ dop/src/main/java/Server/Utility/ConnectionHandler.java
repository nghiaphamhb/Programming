package Server.Utility;

import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.Database.DatabaseRoleManager;
import Server.Manager.Database.DatabaseUserManager;
import Server.Manager.Memory.CommandManager;
import Server.Network.*;
import Server.ServerApp;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Level;

/**
 * Handles user connection.
 */
public class ConnectionHandler implements Runnable{
    private Server server;
    private CommandManager commandManager;
    private DatabaseUserManager databaseUserManager;
    private DatabaseRoleManager databaseRoleManager;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public ConnectionHandler(Server server, CommandManager commandManager, DatabaseUserManager databaseUserManager,
                             DatabaseRoleManager databaseRoleManager) {
        this.server = server;
        this.commandManager = commandManager;
        this.databaseUserManager = databaseUserManager;
        this.databaseRoleManager =databaseRoleManager;
    }

    /**
     * Run the handler
     */
    @Override
    public void run() {
        Request requestFromUser = null;
        Response responseToUser = null;
        do {
            //Get request
            byte[] dataFromUser = server.receiveData();
            requestFromUser = (Request) SerializationUtils.deserialize(dataFromUser);
            ServerApp.logger.log(Level.INFO, "Request received: " + requestFromUser);

            //Process request and make response
            try {
                Future<Response> responseFuture = fixedThreadPool.submit(new RequestHandler(commandManager, requestFromUser,databaseUserManager, databaseRoleManager));
                responseToUser = responseFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                ServerApp.logger.log(Level.WARNING, e.toString());
            }
            ServerApp.logger.info("Request '" + requestFromUser.getNameCommand() + "' processed.");

            //Send response to user
            Response finalResponseToUser = responseToUser;
            byte[] dataToUser = SerializationUtils.serialize(finalResponseToUser);
            ServerApp.logger.log(Level.INFO, "Response from server: " + finalResponseToUser);
            try {
                if (!cachedThreadPool.submit(() -> {
                    try{
                        server.sendData(dataToUser);
                        return true;
                    } catch (IOException e) {
                        ServerApp.logger.log(Level.WARNING, "An error occurred while sending response to the client");
                    }
                    return false;
                }).get() ) break;
            } catch (InterruptedException | ExecutionException e) {
                ServerApp.logger.log(Level.WARNING, e.toString());
            }

        } while (responseToUser.getResponseCode() != ProgramCode.SERVER_EXIT &&
                responseToUser.getResponseCode() != ProgramCode.CLIENT_EXIT);
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        ServerApp.logger.log(Level.INFO, "Client disconnect to server.");
    }
}
