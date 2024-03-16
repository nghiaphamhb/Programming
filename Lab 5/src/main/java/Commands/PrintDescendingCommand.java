package Commands;

public class PrintDescendingCommand extends Commands {
    public PrintDescendingCommand() {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
    }



    @Override
    public boolean execute(String[] argument) {
        return false;
    }
}
