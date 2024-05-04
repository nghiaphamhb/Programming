package Server.Utility;

import Common.Network.Request;
import Common.Network.Response;
import Server.Manager.CommandManager;
import Server.Commands.*;
import java.util.logging.Logger;

//class dùng để xử lí Request và tạo Response
public class SvHandler {
    private final CommandManager commandManager;
    private final Logger logger;


    public SvHandler(CommandManager commandManager, Logger logger) {
        this.commandManager = commandManager;
        this.logger = logger;
    }

    public Response handle(Request request) {
        Commands command = commandManager.getByName(request.getNameCommand());
        if (command == null) return new Response("That command is not existed.");
        return command.execute(request);
    }


}
