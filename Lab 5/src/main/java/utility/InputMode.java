package utility;

/**
 * Setting the mode of input
 */
public class InputMode {
    private static boolean fileMode = false;

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
        InputMode.fileMode = false;
    }

    /**
     * Turn on mode: import data from file
     */
    public static void setFileMode() {
        InputMode.fileMode = true;
    }
}
