package Utility;

/**
 * Отображение на экране терминала,
 */
public class Display {
    private static final String PS1 = "$ ";
    private static final String PS2 = "> ";

    /**
     * Печатать объекта
     * @param obj объект
     */
    public static void print( Object obj ) {
        System.out.print( obj );
    }

    /**
     * Печатать объекта вниз по строке
     * @param obj объект
     */
    public static void println(Object obj) {
        System.out.println( obj );
    }

    /**
     * Печатать ошибку
     * @param obj ошибка
     */
    public static void printError( Object obj ) {
        System.out.println( "Ошибка: " + obj );
    }

    /**
     * Печатать таблицу, в которой 2 столбцы
     * @param elementLeft 1- объект (название)
     * @param elementRight 2- объект (описание)
     */
    public static void printTable( Object elementLeft, Object elementRight ) {
        System.out.printf( " %-35s %-1s %n", elementLeft, elementRight );
    }

    /**
     * Печатать знак $
     */
    public static void ps1() {
        print(PS1);
    }

    /**
     * Печатать знак >
     */
    public static void ps2() {
        print(PS2);
    }


}
