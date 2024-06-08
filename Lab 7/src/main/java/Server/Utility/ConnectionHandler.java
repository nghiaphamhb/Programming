package Server.Utility;

import Client.Utility.Display;
import Common.Network.ProgramCode;
import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;
import Server.Network.*;
import Server.ServerApp;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

// xu li cac nhiem vu da luong
public class ConnectionHandler implements Runnable{
    private Server server;
    private CommandManager commandManager;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public ConnectionHandler(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        Request requestFromUser = null;
        Response responseToUser = null;
        Display.printError("Connection Handler is running.");
//        boolean isStopped = false;
        do {
            byte[] dataFromUser = server.receiveData();
            requestFromUser = (Request) SerializationUtils.deserialize(dataFromUser);
            Display.println("Da nhan duoc request: " + requestFromUser);
            //nhan request
            Future<Response> responseFuture = fixedThreadPool.submit(new RequestHandler(commandManager, requestFromUser));
            try {
                responseToUser = responseFuture.get();
                Display.println("Da tao duoc response:" + responseToUser);
            } catch (InterruptedException e) {
                Display.println(e);
            } catch (ExecutionException e) {
                Display.println(e);
            }
            ServerApp.logger.info("Запрос '" + requestFromUser.getNameCommand() + "' обработан.");

            //xu li du lieu
            Response finalResponseToUser = responseToUser;
            //gui phan hoi toi user
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
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        } while (responseToUser.getResponseCode() != ProgramCode.SERVER_EXIT &&
                responseToUser.getResponseCode() != ProgramCode.CLIENT_EXIT);
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        ServerApp.logger.log(Level.INFO, "Client disconnect to server.");
    }
}
