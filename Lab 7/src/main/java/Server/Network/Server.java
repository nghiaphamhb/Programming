package Server.Network;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

import Server.ServerApp;

/**
 * Server
 */
public class Server {
    private final int PACKET_SIZE = 2048;
    private final SocketAddress serverAddr;
    private SocketAddress clientAddr;
    private ServerSender serverSender;
    private ServerReceiver serverReceiver;
    private DatagramChannel dc;



    public Server(InetAddress hostAddr, int port) throws IOException {
        this.serverAddr = new InetSocketAddress(hostAddr, port);
        this.clientAddr = null;
        this.dc = DatagramChannel.open();
        try {
            dc.setOption(StandardSocketOptions.SO_REUSEADDR, true).bind(serverAddr);
        } catch (BindException e){
            ServerApp.logger.log(Level.WARNING, e.toString());
        }
        this.serverReceiver = new ServerReceiver(PACKET_SIZE, dc);
    }

    public DatagramChannel getDc() {
        return dc;
    }

    /**
     * Server connects to client
     * @return client address
     */
    public SocketAddress connectToClient(){
        try {
            dc.connect(clientAddr);
            this.serverSender = new ServerSender(PACKET_SIZE, dc, clientAddr);
            return clientAddr;
        } catch (IOException e) {
            ServerApp.logger.log(Level.SEVERE, "Connection failed!");
        }
        return null;

    }

    /**
     * Server disconnects from client
     */
    public void disconnectFromClient() {
        try {
            dc.disconnect();
        } catch (IOException e){
            ServerApp.logger.log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Server clears data socket
     */
    public void clearSocket(){
        try {
            dc.close();
        } catch (IOException e){
            ServerApp.logger.log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Server receives data from client
     * @return received data
     */
    public byte[] receiveData() {
        byte[] data = serverReceiver.receiveData();
        this.clientAddr = serverReceiver.getClientAddr();
        this.serverSender = new ServerSender(PACKET_SIZE, dc, clientAddr);
        return data;
    }

    /**
     * Server sends data to client
     * @param data data which need to send
     */
    public void sendData(byte[] data) throws IOException {
        serverSender.sendData(data);
    }

    /**
     * Server connects to client
     * @return if server is connected
     */
    public boolean isConnected(){
        return dc.isConnected();
    }
}