package utility.Asker;

import java.util.Scanner;

//nhập dữ liệu người
// cả class là static
public class Asker {
    private static Scanner scanner;
    private static boolean fileMode = false;

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void setScanner(Scanner scanner) {
        Asker.scanner = scanner;
    }
    public static boolean isFileMode() {
        return fileMode;
    }
    public static void setUserMode() {
        Asker.fileMode = false;
    }
    public static void setFileMode() {
        Asker.fileMode = true;
    }
}
