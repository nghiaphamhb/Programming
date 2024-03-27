package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads content from script file
 */
public class ScriptReader {
    private static BufferedInputStream inputStream;

    /**
     * Set up input's stream
     * @param filePath location's script file
     */
    public static void setInputStream(String filePath) {
        try{
            if (!new File(filePath).exists()) {
                filePath = "src/main/resources/" + filePath;
            }

            FileInputStream fileInputStream = new FileInputStream(filePath);
            inputStream = new BufferedInputStream( fileInputStream );
        } catch (FileNotFoundException e) {
            Console.printError("Этот файл не существует.");
        }
    }

    /**
     * Reads the string flow from the script file
     * @return string flow
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
            Console.printError("Содержание файла пусто!");
        }
        return content;
    }

    /**
     * string processing (Separate the string into lines)
     * @return lines
     */
    public static String[] getLines() {
        return getContent().toString().split("\\r?\\n");
    }

    /**
     * Remove empty lines
     * @return lines without empty
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
            Console.printError("Ошибка закрытия резьбы");
        }

        return scriptLines;
    }




}
