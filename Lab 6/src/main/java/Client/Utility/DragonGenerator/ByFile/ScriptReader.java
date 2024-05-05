package Client.Utility.DragonGenerator.ByFile;

import Client.Utility.Display;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Инструмент для чтения скрипта
 */
public class ScriptReader {
    private static Logger logger;
    private static BufferedInputStream inputStream;

    public static void setLogger(Logger logger) {
        ScriptReader.logger = logger;
    }

    /**
     * Подключить к скрипту
     * @param filePath адрес у скрипта
     */
    public static void setInputStream(String filePath) {
        try{
            if (!new File(filePath).exists()) {
                filePath = "src/main/java/Client/" + filePath;
            }

            FileInputStream fileInputStream = new FileInputStream(filePath);
            inputStream = new BufferedInputStream( fileInputStream );
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING,"File " + filePath + " does not exist." );
        }
    }

    /**
     * Читать содержание в скрипте
     * @return содержание
     */
    public static StringBuilder getContent() {
        StringBuilder content = new StringBuilder();
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ( ( bytesRead = inputStream.read(buffer) ) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }
        } catch ( IOException e ) {
            logger.log(Level.WARNING,"The contents of the file are empty!" );
        }
        return content;
    }

    /**
     * Обработать полученное содержание (разделить его на строки)
     * @return строки из содержания
     */
    public static String[] getLines() {
        return getContent().toString().split("\\r?\\n");
    }

    /**
     * Удалить пустую строку
     * @return сбор строк без пустой
     */
    public static List<String> getScriptLines() {
        List<String> scriptLines = new ArrayList<>();
        try{
            for ( String line : getLines() ) {
                if (!line.trim().isEmpty()) {
                    scriptLines.add(line);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            logger.log(Level.WARNING,"Thread closing error" );
        }

        return scriptLines;
    }




}
