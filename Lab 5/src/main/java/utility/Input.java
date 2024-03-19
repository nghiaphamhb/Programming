package utility;

import java.util.Scanner;

/**
 * This is the class that contains the input and adjusts its state
 */
public class Input {
    private static boolean fileMode = false;
    private static Scanner scanner;

    /**
     * Turn on the scanner
     * @return scanner to import data
     */
    public static Scanner getScanner() {
        return new Scanner( System.in );
    }

    /**
     * Check if the "import from file" mode is active then
     * @return state of input
     */
    public static boolean isFileMode() {
        return fileMode;
    }

    /**
     * Turn on mode: import data from asking user
     */
    public static void setUserMode() {
        Input.fileMode = false;
    }

    /**
     * Turn on mode: import data from file
     */
    public static void setFileMode() {
        Input.fileMode = true;
    }
}
