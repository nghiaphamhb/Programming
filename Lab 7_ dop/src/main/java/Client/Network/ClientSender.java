package Client.Network;

import Client.Utility.Display;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/**
 * Client's sender
 */
public class ClientSender {
    private final int DATA_SIZE;
    private final DatagramChannel datagramChannel;
    private final SocketAddress serverAddress;

    public ClientSender(int PACKET_SIZE, DatagramChannel datagramChannel, SocketAddress serverAddress) {
        this.DATA_SIZE = PACKET_SIZE - 1;
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
    }

    /**
     * send data to server
     * @param data data
     */
    public void sendData(byte[] data) {
        int number_chunk = (int)Math.ceil((double) data.length /DATA_SIZE);
        byte[][] chunk = new byte[number_chunk][DATA_SIZE];

        int start = 0;
        for(int i = 0; i < number_chunk; i++) {
            chunk[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }

        int PACKET_SIZE = DATA_SIZE + 1;
        for (int i = 0; i < number_chunk; i++){
            byte[] tempChunk = chunk[i];
            if (i == number_chunk - 1){
                ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
                buffer.put(tempChunk);
                buffer.put(new byte[]{1});
                byte[] lastChunk = buffer.array();

                try {
                    datagramChannel.send(ByteBuffer.wrap(lastChunk), serverAddress);
                } catch (IOException e){
                    Display.printError("Failure to receive response from Server.");
                }

            } else {
                ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
                buffer.put(tempChunk);
                buffer.put(new byte[]{0});
                byte[] sentChunk = buffer.array();

                try {
                    datagramChannel.send(ByteBuffer.wrap(sentChunk), serverAddress);
                } catch (IOException e){
                    Display.printError("Failure to receive response from Server.");
                }
            }
        }
    }
}
