package Client.Network;

import Client.Utility.Display;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliReceiver {
    private final int PACKET_SIZE;
    private final DatagramChannel dc;
    private final Logger logger;

    public CliReceiver(int PACKET_SIZE, DatagramChannel dc, Logger logger) {
        this.PACKET_SIZE = PACKET_SIZE;
        this.dc = dc;
        this.logger = logger;
    }

    public byte[] receiveData() {
        boolean dataIsReceived = false;
        byte[] result = new byte[0];

        while(!dataIsReceived) {
            byte[] data = receivedData(PACKET_SIZE);  //exception
            logger.log(Level.INFO, "Receiving response from server..." );


            if (data[data.length - 1] == 1) {
                dataIsReceived = true;
                logger.log(Level.INFO, "Response getting from server completed.");
            }
            ByteBuffer buffer = ByteBuffer.allocate(result.length + data.length);
            buffer.put(result);
            buffer.put(data);
            result = buffer.array();
        }

        return result;
    }

    private byte[] receivedData(int bufferSize)  {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        SocketAddress serverAddress = null;
        while (serverAddress == null) {
            try {
            serverAddress = dc.receive(buffer);  //exception
            } catch (IOException e) {
                logger.log(Level.WARNING, "Client that bai trong viec nhan du lieu");
            }
        }
        buffer.flip();
        return buffer.array();
    }
}
