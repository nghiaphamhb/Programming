package Server.Network;

import Client.Utility.Display;
import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.logging.Level;

import Server.ServerApp;

/**
 * Server's receiver
 */
public class ServerReceiver {
    private final int PACKET_SIZE;
    private final DatagramChannel dc;
    private SocketAddress clientAddr = null;

    public ServerReceiver(int PACKET_SIZE, DatagramChannel dc) {
        this.PACKET_SIZE = PACKET_SIZE;
        this.dc = dc;
    }

    /**
     * Receive data
     * @return received data
     */
    public byte[] receiveData()  {
        var received = false;
        var result = new byte[0];

        while(!received) {
            try {
                var buf = ByteBuffer.allocate(PACKET_SIZE);
                buf.clear();
                clientAddr = dc.receive(buf);
                if (clientAddr == null) throw new IOException();
                byte[] data = buf.array();

                ServerApp.logger.log(Level.INFO, "Server successfully connected to the client " + clientAddr);
                ServerApp.logger.info("Getting request from client" + clientAddr);

                if (data[data.length - 1] == 1) {
                    received = true;
                    ServerApp.logger.info("Request getting from client was done");
                }
                result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
            } catch (IOException e) {
                continue;
            }

        }
        return result;
    }

    /**
     * Get client address
     * @return client's address
     */
    public SocketAddress getClientAddr() {
        return clientAddr;
    }
}
