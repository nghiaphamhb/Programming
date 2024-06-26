package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;
import Server.Utility.Role;

/**
 * Command interface
 */
public interface CommandInterface {
    Response execute(Request request, Role role);
}
