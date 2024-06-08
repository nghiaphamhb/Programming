package Client.Utility;

/**
 * Display on the terminal screen
 */
public class Display {
    private static final String PS1 = "$ ";
    private static final String PS2 = "> ";

    /**
     * Print the object
     * @param obj object
     */
    public static void print( Object obj ) {
        System.out.print( obj );
    }

    /**
     * Print the object down the line
     * @param obj object
     */
    public static void println(Object obj) {
        System.out.println( obj );
    }

    /**
     * Print an error
     * @param obj error
     */
    public static void printError( Object obj ) {
        System.out.println( "Error: " + obj );
    }

    /**
     * Print a table with 2 columns
     * @param elementLeft Left 1- object (name)
     * @param elementRight Right 2- object (description)
     */
    public static void printTable( Object elementLeft, Object elementRight ) {
        System.out.printf( " %-35s %-1s %n", elementLeft, elementRight );
    }

    /**
     * Print the sign $
     */
    public static void ps1() {
        print(PS1);
    }

    /**
     * Print the sign >
     */
    public static void ps2() {
        print(PS2);
    }


}
