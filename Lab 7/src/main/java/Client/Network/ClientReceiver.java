package Client.Network;

import Client.Utility.Display;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * client's receiver
 */
public class ClientReceiver {
    private final int PACKET_SIZE;
    private final DatagramChannel datagramChannel;

    public ClientReceiver(int PACKET_SIZE, DatagramChannel datagramChannel) {
        this.PACKET_SIZE = PACKET_SIZE;
        this.datagramChannel = datagramChannel;
    }

    /**
     * receive data from server
     * @return all received data
     */
    public byte[] receiveData() {
        boolean dataIsReceived = false;
        byte[] result = new byte[0];

        while(!dataIsReceived) {
            byte[] data = receivedData(PACKET_SIZE);  //exception
            if (data == null) return null;
            if (data[data.length - 1] == 1) {
                dataIsReceived = true;
            }
            ByteBuffer buffer = ByteBuffer.allocate(result.length + data.length);
            buffer.put(result);
            buffer.put(data);
            result = buffer.array();
        }
        return result;
    }

    /**
     * receive data chunks from server
     * @return chunk of received data
     */
    private byte[] receivedData(int bufferSize)  {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        SocketAddress serverAddress = null;
        while (serverAddress == null) {
            try {
            serverAddress = datagramChannel.receive(buffer);  //exception
            } catch (IOException e) {
                Display.printError("Server is not accessible.");
                return null;
            }
        }
        buffer.flip();
        return buffer.array();
    }
}
