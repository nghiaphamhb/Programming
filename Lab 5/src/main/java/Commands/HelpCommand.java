package Commands;

import Manager.CommandManager;
import utility.*;

public class HelpCommand extends Commands {
    private final CommandManager commandManager;
//    Nếu commandManager được khai báo là final, bạn sẽ không thể gán lại giá trị cho nó sau
//    khi constructor đã thực hiện gán giá trị ban đầu. Điều này có nghĩa là bạn không thể thay
//    đổi đối tượng mà commandManager tham chiếu đến, nhưng bạn vẫn có thể thay đổi trạng thái nội
//    bộ của đối tượng đó (ví dụ: thêm hoặc xóa các phần tử trong commandManager, nếu commandManager
//                         là một cấu trúc dữ liệu có thể thay đổi).
//final : giữ nguyên đường tham chiếu tới đối tượng
    public HelpCommand(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }
    @Override
    public boolean execute (String[] argument){
        if ( !argument[1].isEmpty() ) {
            Console.println("Использование: " + getName() );
            return false;
        }
        else {
            for (Commands c : commandManager.getCommands()){
                Console.printTable(c.getName(), c.getDescription());
            }
            return true;
        }
//        commandManager.getCommands().forEach( commands -> Console.printTable( commands.getName(), commands.getDescription() ));
//        Trong Java, phương thức forEach() của một Iterable (bao gồm HashSet) yêu cầu một đối tượng là một functional interface như tham số. Trong trường hợp của phương thức forEach() trong lớp HashSet, nó yêu cầu một đối tượng là một functional interface Consumer.
//
//        Biểu thức lambda có thể được sử dụng để tạo ra một thể hiện của functional interface một cách rất ngắn gọn. Trong ví dụ của bạn, command -> console.printTable(command.getName(), command.getDescription()) là một biểu thức lambda triển khai phương thức của
//        functional interface Consumer
    }
}
