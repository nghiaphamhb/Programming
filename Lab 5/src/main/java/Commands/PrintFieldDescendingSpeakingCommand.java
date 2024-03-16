package Commands;

public class PrintFieldDescendingSpeakingCommand extends Commands {
    public PrintFieldDescendingSpeakingCommand() {
        super("print_field_descending_speaking", "вывести значения поля speaking всех элементов в порядке убывания");
    }



    @Override
    public boolean execute(String[] argument) {
        return false;
    }
}
