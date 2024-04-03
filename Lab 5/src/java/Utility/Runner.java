package Utility;

import Manager.*;
import Commands.*;
import Exception.*;
import Utility.Input.Console;
import Utility.Input.ByFile.FileInput;
import Utility.Input.Input;
import Utility.Input.ByFile.ScriptReader;

import java.util.*;

/**
 * Запускает программу
 */
public class Runner {
    /**
     * Статус программы
     */
    public enum ExitCode {
        RUN,
        ERROR,
        EXIT
    }

    private final CommandManager commandManager;
    private DragonManager dragonManager;
    private Input input;
    private List<String> scriptList; //List of scripts used


    public Runner(CommandManager commandManager, DragonManager dragonManager) {
        this.commandManager = commandManager;
        this.dragonManager = dragonManager;
        this.input = new Console();
        this.scriptList  = new ArrayList<>();
        CommandRegister();
    }

    /**
     * Автоматический запуск программы
     */
    public void start(){
        ExitCode commandStatus = null;
        String[] command;
        do {
            try {
                Display.ps1();
                Scanner scanner = new Scanner(System.in);
                command = argumentToCommand(scanner.nextLine());

                commandStatus = updateProgramStatus(command);
            } catch (CommandIsNotFoundException e) {
                Display.printError("Эта команда не существует. Наберите 'help' для справки");
            }
        } while (commandStatus != ExitCode.EXIT);
    }

    /**
     * Инициализировать все команды
     */
    private void CommandRegister (){
        this.commandManager.register( new AddCommand(dragonManager, input) );
        this.commandManager.register( new AddIfMaxCommand(dragonManager, input) );
        this.commandManager.register( new AddIfMinCommand(dragonManager, input) );
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
        this.commandManager.register( new SaveCommand(dragonManager) );
        this.commandManager.register( new ShowCommand(dragonManager) );
        this.commandManager.register( new UpdateIdCommand(dragonManager, input) );
    }

    /**
     * Запустить скрипт (команда execute_script)
     * @param filePath путь к скрипту
     * @return статус программы
     */
    private ExitCode runScript (String filePath)  {
        List<String> scriptLines;
        String[] command;
        ExitCode commandStatus = null;

        try {
            //register file's path to history
            scriptList.add(filePath);

            //Connect to file and get lines from script file
            ScriptReader.setInputStream(filePath);
            scriptLines = ScriptReader.getScriptLines();

            //Separate every command lines to 2 parts: keyword + argument
            for (int i = 0; i < scriptLines.size(); i++) {
                command = argumentToCommand(scriptLines.get(i));

                Display.ps1();
                Display.println( scriptLines.get(i) );  // "> keyword"

                // Get dragon's information from file
                if ( Arrays.asList( "add", "add_if_max", "add_if_min" ).contains( command[0] ) && command[1].isEmpty() ) {
                    String[] info = scriptLines.subList(i+1,  i+9).toArray( new String[8] );
                    updateInput( new FileInput(info));
                    i += 8;
                }

                if ( command[0].equals("execute_script") ) {
                    for ( String script : scriptList ) {
                        if ( command[1].equals(script) ) throw new ScriptRecursionException();
                    }
                }

                commandStatus = updateProgramStatus(command);
            }
        } catch (ScriptRecursionException e) {
            Display.printError("Скрипты не могут вызываться рекурсивно!");
        }
        updateInput( new Console() );
        return commandStatus;
    }

    /**
     * Обработать входную символьную строку
     * @param argument входная символьная строка
     * @return команда
     */
    private String[] argumentToCommand(String argument){
        String[] command = {};
        try{
            command = (argument.trim() + " ").split(" ", 2);
            if ( command[0].isEmpty() ) throw new NoSuchElementException();
            if (command.length > 1) command[1] = command[1].trim();
        } catch (NoSuchElementException exception) {
            Display.print("");
        }
        return command;
    }

    /**
     * Обновить ввод
     * @param input ввод
     */
    private void updateInput(Input input) {
        this.input = input;
        this.commandManager.clear();
        CommandRegister();
    }
    /**
     * Обновить статус программы после выполнения команды
     * @param command команда
     * @return статус программы
     * @throws CommandIsNotFoundException не удалось найти команду
     */
    private ExitCode updateProgramStatus (String[] command)  {
        try {
            if ( !commandManager.activateCommand(command) ) throw new CommandIsNotFoundException();
            switch (command[0]){
                case "exit":
                    if (command[1].isEmpty()) return ExitCode.EXIT;
                    else return ExitCode.ERROR;
                case "execute_script":
                    if (command[1].isEmpty()) return ExitCode.ERROR;
                    else return runScript(command[1]);
            }
            return ExitCode.RUN;
        } catch (CommandIsNotFoundException e) {
            if ( !command[0].isEmpty() ){
                Display.printError("Команда " + command[0] + " не существует. Наберите 'help' для справки");
            } else {
                Display.print("");
            }
        }
        return ExitCode.ERROR;
    }
}
