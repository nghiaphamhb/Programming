package Server.Utility;

import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;
import Server.Manager.DragonCollection;
import Server.Manager.StandardCommandManager;
import Server.Network.Server;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;

import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class of operation of the program
 */
public class SvRunner implements Runnable{
    private final CommandManager commandManager;
    private final DragonCollection dragonCollection;
    private final SvHandler svHandler;
    private Server server;
    private Logger logger;
    private boolean serverIsRunning = true;
    private SocketAddress clientAddr = null;


    public SvRunner(DragonCollection dragonCollection, Server sv, Logger log) {
        this.dragonCollection = dragonCollection;
        this.commandManager = new CommandManager(dragonCollection);
        this.logger = log;
        this.svHandler = new SvHandler(commandManager, logger);
        this.server = sv;
    }

    /**
     * Run the program
     */
    public void run() {

        while (serverIsRunning) {
            //taking data from client (request)
            byte[] dataFromClient = server.receiveData();
            //connecting
            if (!server.serverIsConnecting()) {
                clientAddr = server.connectToClient();
            }

            //unpacketing request
            Request requestFromClient = null;
            try {
                requestFromClient = (Request) SerializationUtils.deserialize(dataFromClient);
                logger.log(Level.INFO, "Processing " + requestFromClient.toString() + " from " + clientAddr);
            } catch (SerializationException e) {
                logger.log(Level.SEVERE, "Could not deserialize request's object");
                stop();
            }

            //processing command and making response
            Response responseToClient = null;
            try {
                responseToClient = svHandler.handle(requestFromClient);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred while processing request: " + e.toString());
            }
            if (responseToClient == null) responseToClient = new Response(null);

            //serialize message from server to client
            byte[] dataToClient = SerializationUtils.serialize(responseToClient);
            logger.log(Level.INFO, "Response from server: " + responseToClient);

            //send message's data to client
            server.sendData(dataToClient);
            logger.log(Level.INFO, "The response has been sent to client: " + clientAddr);

            //if this is exit command, so disconnect to client and program will be ended
            if (requestFromClient.getNameCommand().equals("exit")) stop();

            server.disconnectFromClient();
        }
    }

    /**
     * Stop the program
     */
    public void stop() {
        dragonCollection.saveCollection();
        server.disconnectFromClient();
        logger.log(Level.INFO, "Server disconnected from client\nThe program was ended.");
        serverIsRunning = false;
        server.clearSocket();
    }



}
