package Commands;

public class SaveCommand extends Commands {
    public SaveCommand() {
        super("save", "сохранить коллекцию в файл");
    }


    @Override
    public boolean execute(String[] argument) {
        return false;
    }
}
