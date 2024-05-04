package Server.Utility;

import Common.Network.Request;
import Common.Network.Response;
import Client.Utility.Display;
import Server.Manager.DragonCollection;
import Server.Commands.*;
import Common.Exception.*;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Server.Manager.CommandManager;
import Server.Network.Server;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Run app
 */
public class SvRunner implements Runnable{
    private final CommandManager commandManager;
    private final DragonCollection dragonCollection;
    private final SvHandler svHandler;
    private Input input;
    private List<String> scriptList; //List of scripts used
    private Server server;
    private Logger logger;
    private boolean serverIsRunning = true;
    private SocketAddress clientAddr = null;


    public SvRunner(CommandManager commandManager, DragonCollection dragonCollection, Server sv, Logger log) {
        this.commandManager = commandManager;
        this.dragonCollection = dragonCollection;
        this.logger = log;
        this.svHandler = new SvHandler(commandManager, logger);
        this.server = sv;
        this.input = new Console();
        this.scriptList  = new ArrayList<>();
        registerCommands();
    }

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
                server.disconnectFromClient();
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

        }
        server.disconnectFromClient();
        logger.log(Level.INFO, "Server disconnected from client");
        server.clearSocket();

    }

    public void stop() {
        serverIsRunning = false;
    }

    /**
     * Инициализировать все команды
     */
    private void registerCommands (){
        this.commandManager.register( new AddCommand(dragonCollection, input) );
        this.commandManager.register( new AddIfMaxCommand(dragonCollection, input) );
        this.commandManager.register( new AddIfMinCommand(dragonCollection, input) );
        this.commandManager.register( new ClearCommand(dragonCollection) );
        this.commandManager.register( new ExecuteScriptCommand() );
        this.commandManager.register( new ExitCommand() );
        this.commandManager.register( new FilterContainsNameCommand(dragonCollection) );
        this.commandManager.register( new HelpCommand(commandManager) );
        this.commandManager.register( new HistoryCommand(commandManager) );
        this.commandManager.register( new InfoCommand(dragonCollection) );
        this.commandManager.register( new PrintDescendingCommand(dragonCollection) );
        this.commandManager.register( new PrintFieldDescendingSpeakingCommand(dragonCollection) );
        this.commandManager.register( new RemoveByIdCommand(dragonCollection) );
        this.commandManager.register( new SaveCommand(dragonCollection) );
        this.commandManager.register( new ShowCommand(dragonCollection) );
        this.commandManager.register( new UpdateIdCommand(dragonCollection, input) );
        this.commandManager.register(new NoSuchCommand());
    }

}
