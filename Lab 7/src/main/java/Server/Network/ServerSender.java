package Server.Network;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.logging.Level;
import Server.ServerApp;
/**
 * Server's sender
 */
public class ServerSender {
    private final int PACKET_SIZE;
    private final DatagramChannel dc;
    private final SocketAddress clientAddr;

    public ServerSender(int PACKET_SIZE, DatagramChannel dc, SocketAddress clientAdrr) {
        this.PACKET_SIZE = PACKET_SIZE;
        this.dc = dc;
        this.clientAddr = clientAdrr;
    }

    /**
     * Send data to client
     * @param data data, which need to send
     */
    public void sendData(byte[] data) throws IOException {
        int DATA_SIZE = PACKET_SIZE - 1;
        byte[][] ret = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];

        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }

        ServerApp.logger.log(Level.INFO, "Splitting the response into " + ret.length +" chunk(s) and sending them to the client...");

        for(int i = 0; i < ret.length; i++) {
            var chunk = ret[i];
                if (i == ret.length - 1) {
                    var lastChunk = Bytes.concat(chunk, new byte[]{1});
                    dc.send(ByteBuffer.wrap(lastChunk), clientAddr);
                    ServerApp.logger.log(Level.INFO, "Last chunk has been sent to client");
                } else {
                    var tempChunk = Bytes.concat(chunk, new byte[]{0});
                    dc.send(ByteBuffer.wrap(tempChunk), clientAddr);
                    ServerApp.logger.log(Level.INFO, "Sending chunks...");
                }


        }

        ServerApp.logger.log(Level.INFO,"Response sending to client complected.");
    }
}
