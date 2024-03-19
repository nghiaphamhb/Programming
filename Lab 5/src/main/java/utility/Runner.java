package utility;

import Manager.*;
import Commands.*;

import java.io.*;
import java.util.*;

public class Runner {
    public enum ExitCode {
        RUN,
        ERROR,
        EXIT
    }
    private final CommandManager commandManager;
    private DragonManager dragonManager;

    public Runner(CommandManager commandManager, DragonManager dragonManager) {
        this.commandManager = commandManager;
        this.dragonManager = dragonManager;
        CommandRegister();
    }

    public void start(){
        ExitCode commandStatus = null;
        String[] command;
        do {
            try {
                Console.ps1();
                command = Input.getScanner().nextLine().trim().split(" ", 2);
                if (command.length > 1) command[1] = command[1].trim();
                // có thể có nhiều hơn 1 dấu cách ở giữa
//                Hàm trim() trong Java là một phương thức của lớp String được sử dụng để loại bỏ
//                các ký tự trắng (bao gồm cả dấu cách, tab và xuống dòng) từ cả hai đầu của một chuỗi.
//                Hàm này không chỉ loại bỏ các ký tự trắng ở đầu và cuối chuỗi mà còn giữ lại các ký
//                tự trắng ở giữa chuỗi.
                commandStatus = runCommand(command);
            } catch (NoSuchElementException exception) {
                Console.printError("Эта команда неправильна!");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (commandStatus != ExitCode.EXIT);

// neu khong nhap doi so thi ctrinh ko chay

    }
    private ExitCode runCommand (String[] command) throws IOException {  //to update status of command
        if (commandManager.activateCommand(command)){
            switch (command[0]){
                case "execute_script":
                    if (command.length ==1) return ExitCode.ERROR;
                    else return scriptMode(command[1]);
                case "exit":
                    if (command.length == 1) return ExitCode.EXIT;
                    else return ExitCode.ERROR;
            }
        }
        else{
            Console.printError("Команда " + command[0] + " не существует. Наберите 'help' для справки");
            return ExitCode.ERROR;
        }
        return ExitCode.RUN;
    }
    private ExitCode scriptMode (String filePath) throws IOException {
        String[] command;
        String[] commandLines;
        ExitCode commandStatus;
        List<String> scriptList = new ArrayList<>(); //Список используемых скриптов
        List<String> commandList = new ArrayList<>();

        Input.setFileMode();
        try {
            scriptList.add(filePath);
            if (!new File(filePath).exists()) {
                filePath = "src/main/resources/" + filePath;
            }

            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            StringBuilder content = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }
            // Phân tách chuỗi thành các phần tử riêng biệt
            commandLines = content.toString().split("\\r?\\n");

            // Xóa các dòng trống và thêm các dòng không trống vào ArrayList
            for (String commandLine : commandLines) {
                if (!commandLine.trim().isEmpty()) { // Kiểm tra xem dòng có rỗng không
                    commandList.add(commandLine);
                }
            }



            for (int i = 0; i < commandList.size(); i++) {
                command = (commandList.get(i).trim() + " ").split(" ", 2);

                Console.ps1();
                Console.println(commandList.get(i));

                if (!command[1].isEmpty()) {
                    command[1] = command[1].trim();
                }

                if ((command[0].equals("add") || (command[0].equals("add_if_max") || (command[0].equals("add_if_min")) && command[1].isEmpty()))) {
                    String[] fileScanner = new String[]{
                            commandList.get(i + 1),
                            commandList.get(i + 2),
                            commandList.get(i + 3),
                            commandList.get(i + 4),
                            commandList.get(i + 5),
                            commandList.get(i + 6),
                            commandList.get(i + 7),
                            commandList.get(i + 8)
                    };
                    i += 8;
                    Asker.getInfoFromFile(fileScanner);
                }

                commandStatus = runCommand(command);
            }


        }catch (FileNotFoundException e){
            Console.printError("Этот файл не существует.");        }
        Input.setUserMode();
        return ExitCode.RUN;
}

    private void CommandRegister (){
        this.commandManager.register( new AddCommand(dragonManager) );
        this.commandManager.register( new AddIfMaxCommand(dragonManager) );
        this.commandManager.register( new AddIfMinCommand(dragonManager) );
        this.commandManager.register( new ClearCommand(dragonManager) );
        this.commandManager.register( new ExecuteScriptCommand() );
        this.commandManager.register( new ExitCommand() );
        this.commandManager.register( new FilterContainsNameCommand(dragonManager) );
        this.commandManager.register( new HelpCommand(commandManager) );
        this.commandManager.register( new HistoryCommand(commandManager) );
        this.commandManager.register( new InfoCommand(dragonManager) );
        this.commandManager.register( new PrintDescendingCommand(dragonManager) );
        this.commandManager.register( new PrintFieldDescendingSpeakingCommand(dragonManager) );
        this.commandManager.register( new RemoveByIdCommand(dragonManager) );
        this.commandManager.register( new SaveCommand() );
        this.commandManager.register( new ShowCommand(dragonManager) );
        this.commandManager.register( new UpdateIdCommand(dragonManager) );
    }
}
