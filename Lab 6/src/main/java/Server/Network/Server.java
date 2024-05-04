package Server.Network;

import Client.Network.CliReceiver;
import Client.Network.CliSender;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private final int PACKET_SIZE = 1024;
    private final Logger logger;
    private final SocketAddress serverAddr;
    private SocketAddress clientAddr;
    private SvSender svSender;
    private SvReceiver svReceiver;
    private DatagramChannel dc;



    public Server(InetAddress hostAddr, int port, Logger log) throws IOException {
        this.serverAddr = new InetSocketAddress(hostAddr, port);
        this.clientAddr = null;
        this.dc = DatagramChannel.open().bind(serverAddr);
        this.logger = log;
        this.svReceiver = new SvReceiver(PACKET_SIZE, dc, logger);
    }




    public SocketAddress connectToClient(){
        try {
            dc.connect(clientAddr);
            this.svSender = new SvSender(PACKET_SIZE, dc, logger, clientAddr);
            return clientAddr;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Connection failed!");
        }
        return null;

    }

    public void disconnectFromClient() {
        try {
            dc.disconnect();
        } catch (IOException e){
            logger.log(Level.SEVERE, e.toString());
        }
    }

    public void clearSocket(){
        try {
            dc.close();
        } catch (IOException e){
            logger.log(Level.SEVERE, e.toString());
        }
    }

    public byte[] receiveData() {
        byte[] data = svReceiver.receiveData();
        this.clientAddr = svReceiver.getClientAddr();
        return data;
    }

    public void sendData(byte[] data){
        svSender.sendData(data);
    }

    public boolean serverIsConnecting(){
        return dc.isConnected();
    }
}
