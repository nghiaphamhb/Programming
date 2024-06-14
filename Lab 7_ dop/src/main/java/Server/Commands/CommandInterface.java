package Server.Commands;

import Common.Data.User;
import Common.Network.Request;
import Common.Network.Response;

/**
 * Command interface
 */
public interface CommandInterface {
    Response execute(Request request);
}
