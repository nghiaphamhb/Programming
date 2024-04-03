package Utility.Input.ByFile;

import Utility.Display;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Инструмент для чтения скрипта
 */
public class ScriptReader {
    private static BufferedInputStream inputStream;

    /**
     * Подключить к скрипту
     * @param filePath адрес у скрипта
     */
    public static void setInputStream(String filePath) {
        try{
            if (!new File(filePath).exists()) {
                filePath = "src/main/resources/" + filePath;
            }

            FileInputStream fileInputStream = new FileInputStream(filePath);
            inputStream = new BufferedInputStream( fileInputStream );
        } catch (FileNotFoundException e) {
            Display.printError("Этот файл не существует.");
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
            Display.printError("Содержание файла пусто!");
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
            Display.printError("Ошибка закрытия резьбы");
        }

        return scriptLines;
    }




}
