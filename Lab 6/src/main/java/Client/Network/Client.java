package Client.Network;
import Common.Network.Request;
import Common.Network.Response;
import Client.Utility.Display;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private final int PACKET_SIZE = 1024;
    private final Logger logger;

    private final SocketAddress serverAddr;
    private DatagramChannel dc;

    private final CliSender cliSender;
    private final CliReceiver cliReceiver;

    private int reconnectionAttempts = 5;

    public Client(InetAddress hostAddress, int port, Logger log) throws IOException {
        this.logger = log;
        this.serverAddr = new InetSocketAddress(hostAddress, port);
        this.dc = DatagramChannel.open().bind(null).connect(serverAddr);
        this.cliSender = new CliSender(PACKET_SIZE, dc, logger, serverAddr);
        this.cliReceiver = new CliReceiver(PACKET_SIZE, dc, logger);
    }


    public Response sendAndReceiveCommand(Request request) {
        byte[] data = SerializationUtils.serialize(request);
        byte[] responseBytes = sendAndReceiveData(data);

        Response response = (Response) SerializationUtils.deserialize(responseBytes); // deserialize return Ojbect type

        logger.log(Level.INFO, "Response from server: ");
        try {
            Thread.sleep(500); // Ngủ 1 giây (1000 miligiây)
        } catch (InterruptedException e) {
            // Xử lý ngoại lệ nếu có
            Thread.currentThread().interrupt(); // Đánh dấu lại interrupt status
        }
        Display.println(response.getMessage());
        return response;
    }



    public byte[] sendAndReceiveData(byte[] data)  {
        cliSender.sendData(data);
        return cliReceiver.receiveData();
    }

    //just to check if datagramChannel ís connected
    public void connectToServer(SocketAddress serverAddr){
        Display.println("Connecting to the server...");
        do {
            try{
                dc = dc.connect(serverAddr);
                dc.configureBlocking(false);

                if (dc.isConnected()){
                    logger.log(Level.INFO, "DataChannel connected to " + serverAddr);
                    break;
                }

                reconnectionAttempts -= 1;
                if (reconnectionAttempts >= 1) Display.println("Connection failed. Reconnecting to the server...");

            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error occurred while connecting to the server!", e);
            }
        } while (reconnectionAttempts != 0);
        if (reconnectionAttempts == 0 ) logger.log(Level.SEVERE, "The connection attempt has expired!");
    }











}
