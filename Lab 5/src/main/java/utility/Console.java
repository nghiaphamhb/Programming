package utility;

/**
 * This is the class to display on the terminal to the user
 */
public class Console {
    private static final String PS1 = "$ ";
    private static final String PS2 = "> ";

    /**
     * Print no down line information of object
     * @param obj (obj.toString()) Objects containing information to display
     */
    public static void print( Object obj ) {
        System.out.print( obj );
    }

    /**
     * Print down line information of object
     * @param obj (obj.toString()) Objects containing information to display
     */
    public static void println(Object obj) {
        System.out.println( obj );
    }

    /**
     * Print down line error
     * @param obj Error
     */
    public static void printError( Object obj ) {
        System.out.println( "Ошибка: " + obj );
    }

    /**
     * Print the table, which has 2 columns ('help' table)
     * @param elementLeft name of command
     * @param elementRight description of this command
     */
    public static void printTable( Object elementLeft, Object elementRight ) {
        System.out.printf( " %-35s %-1s %n", elementLeft, elementRight );
    }

    /**
     * Print sign $
     */
    public static void ps1() {
        print(PS1);
    }

    /**
     * Print sign >
     */
    public static void ps2() {
        print(PS2);
    }


}
