package Server.Commands;

import Common.Network.Request;
import Common.Network.Response;

/**
 * Интерфейс команд
 */
public interface Command_Interface {
    Response execute(Request request);
}
