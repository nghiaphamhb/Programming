package Commands;

public class UpdateIdCommand extends Commands {
    public UpdateIdCommand() {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public boolean execute(String[] argument) {
        return false;
    }
}
