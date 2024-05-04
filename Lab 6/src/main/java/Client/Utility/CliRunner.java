package Client.Utility;

import Client.Network.Client;
//import Client.Commands.*;
import Client.Utility.DragonGenerator.ByFile.FileInput;
import Client.Utility.DragonGenerator.ByFile.ScriptReader;
import Client.Utility.DragonGenerator.ByUser.Console;
import Client.Utility.DragonGenerator.Input;
import Common.Exception.CommandIsNotFoundException;
import Common.Exception.ScriptRecursionException;
import Common.Network.Response;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Запускает программу
 */
public class CliRunner implements Runnable{
    private final Client client;
    private final Logger logger;
    private final CliHandler cliHandler;
//    private HashSet<Commands> commands;
    private Input input;
    private List<String> scriptList; //List of scripts used


    public CliRunner(Client client, Logger logger) {
        this.client = client;
        this.logger = logger;
        this.input = new Console();
        this.cliHandler = new CliHandler(client, logger, input);
        this.scriptList = new ArrayList<>();
    }

    @Override
    public void run(){
        ProgramStatus commandStatus = null;
        do {
            try {
                Display.ps1();
                Scanner scanner = new Scanner(System.in);
                String[] command = argumentToCommand(scanner.nextLine());

                commandStatus = updateProgramStatus(command);

                if (commandStatus != ProgramStatus.ERROR) {
                    Response response = cliHandler.handle(command);
                    Display.println(response);
                }

            } catch (CommandIsNotFoundException e) {
                Display.printError("This command is not exist. Enter 'help' for helping");
            }
        } while (commandStatus != ProgramStatus.EXIT);
    }

    /**
     * Обновить статус программы после выполнения команды
     * @param command commands
     * @return статус программы
     * @throws CommandIsNotFoundException не удалось найти команду
     */
    private ProgramStatus updateProgramStatus (String[] command)  {
        try {
//            if (getByName(command[0]) == null) throw new CommandIsNotFoundException();
            switch (command[0]){
                case "exit":
                    if (command[1].isEmpty()) return ProgramStatus.EXIT;
                    else return ProgramStatus.ERROR;
                case "execute_script":
                    if (command[1].isEmpty()) return ProgramStatus.ERROR;
                    else return runScript(command[1]);
            }
            return ProgramStatus.RUN;
        } catch (CommandIsNotFoundException e) {
            if (!command[0].isEmpty()){
                Display.printError("Command " + command[0] + " is not exist. Enter 'help' for helping");
            } else {
                Display.print("");
            }
        }
        return ProgramStatus.ERROR;
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
     * Запустить скрипт (команда execute_script)
     * @param filePath путь к скрипту
     * @return статус программы
     */
    private ProgramStatus runScript (String filePath)  {
        List<String> scriptLines;
        String[] command;
        ProgramStatus commandStatus = null;

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
     * Обновить ввод
     * @param input ввод
     */
    private void updateInput(Input input) {
        this.input = input;
//        this.commands.clear();
//        this.commands = registerCommand();
    }

}