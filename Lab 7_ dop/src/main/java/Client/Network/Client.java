package Client.Network;
import Common.Network.Request;
import Common.Network.Response;
import Client.Utility.Display;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;

/**
 * Client class to connect, send, get data with server
 */
public class Client {
    private final int PACKET_SIZE = 1024*2;

    private final SocketAddress serverAddress;
    private DatagramChannel datagramChannel;
    private ClientSender clientSender;
    private ClientReceiver clientReceiver;

    public Client(InetAddress hostAddress, int serverPort) throws IOException {
        this.serverAddress = new InetSocketAddress(hostAddress, serverPort);

    }

    /**
     * Check and notice if DatagramChannel is connected
     */
    public void connectToServer(){
        Display.println("DatagramChannel is connecting to the server...");
        while(true){
            try{
                datagramChannel = DatagramChannel.open().bind(null).connect(serverAddress);
                datagramChannel.configureBlocking(false);
                if (datagramChannel.isConnected()){
                    Display.println("DataChannel connected to " + serverAddress);
                    clientSender = new ClientSender(PACKET_SIZE, datagramChannel, serverAddress);
                    clientReceiver = new ClientReceiver(PACKET_SIZE, datagramChannel);
                    break;
                }
            } catch (IOException e) {
                Display.printError("An error occurred while connecting to the server!");
            }
        }
    }

    /**
     * Disconnect to server
     */
    public void disconnectToServer() {
        Display.println("DatagramChannel disconnected to Server");
        try {
            datagramChannel.disconnect();
        } catch (IOException e) {
            Display.printError("Could not disconnect to Server");
        }
    }

    /**
     * send and receive request (command)
     * @param request command to server
     * @return response from server
     */
    public Response sendAndReceiveCommand(Request request) {
        Response response = null;
            byte[] data = SerializationUtils.serialize(request);
            byte[] responseBytes = sendAndReceiveData(data);
            if (responseBytes == null) {
                Display.printError("Response bytes is null!");
                return null;
            }
            response = (Response) SerializationUtils.deserialize(responseBytes);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        return response;
    }

    /**
     * send and receive byte data to and from server
     * @param data sent byte data
     * @return received byte data
     */
    private byte[] sendAndReceiveData(byte[] data)  {
        clientSender.sendData(data);
        return clientReceiver.receiveData();
    }
}
