package Client;


import Client.Network.Client;
import Client.Utility.Display;
import Client.Utility.ClientAppRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Main class
 */
public class ClientApp {
    private static final int serverPORT = 1234;

    public static void main( String[] args){
        try {
            Client client = new Client(InetAddress.getLocalHost(), serverPORT);
            ClientAppRunner app = new ClientAppRunner(client);
            app.run();
        } catch (UnknownHostException e) {
            Display.printError("Host unknown!");
        } catch (IOException e) {
            Display.printError("Unable to connect to the server!");
        }

    }
}
