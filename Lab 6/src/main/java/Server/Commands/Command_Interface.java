package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;

/**
 * Command interface
 */
public interface Command_Interface {
    Response execute(Request request);
}
