package utility;
import Data.*;

import java.lang.IllegalArgumentException;
import java.util.*;
import java.util.InputMismatchException;

/**
 * This is class for asking user (or for inputting file) about the properties of dragon
 */
public class Asker {
    public static Set<Long> idSet = new HashSet<>();
    private static String[] infoFromFile;


    /**
     * Get new information's dragon from file
     * @param info lines of information's dragon
     */
    public static void getInfoFromFile ( String[] info ) {
        infoFromFile = info;
    }

    /**
     * Get random number id (cannot be null; be >0; be unique; be generated automatically)
     * @return number id's dragon
     */
    public static long askId() {
        Long id = null;
        do {
            Random random = new Random();
            long maxValue = 1000;
            long minValue = 0;
            id = minValue + (long) ( random.nextDouble() * ( maxValue - minValue ) );
        } while ( id <= 0 || checkId(id) );
        return id;
    }

    /**
     * Ask question and get name's dragon from input (name cannot be null or empty)
     * @return name's dragon
     */
    public static String askName(){
        String name = null;
        Console.println( "Как зовут дракона?" );
        do {
            try {
                Console.ps2();
                if ( Input.isFileMode() ) {
                    name = infoFromFile[0];
                    Console.println( name );
                } else {
                    name = Input.getScanner().nextLine();
                }
            } catch ( InputMismatchException e ){
                Console.printError("Введен неверный тип данных.");
            }
        } while ( name == null || name.isEmpty() );
        return name;
    }

    /**
     * Ask question and get abs's dragon from input ( cannot be null)
     * @return abs's dragon
     */
    public static Integer askAbs(){
        Integer abs = null;
        Console.println("+Абсцисса дракона:");
        do {
            try {
                Console.ps2();

                if ( Input.isFileMode() ){
                    abs = Integer.valueOf( infoFromFile[1] );
                    Console.println( abs );
                } else {
                    abs = Input.getScanner().nextInt();
                }
            } catch ( InputMismatchException e ){
                Console.printError("Введен неверный тип данных.");
            }
        } while ( abs == null );
        return abs;
    }

    /**
     * Ask question and get ord's dragon from input ( cannot be null)
     * @return ord's dragon
     */
    public static Long askOrd(){
        Long ord = null;
        Console.println("+Ордината дракона:");

        do {
            try {
                Console.ps2();

                if ( Input.isFileMode() ) {
                    ord = Long.valueOf( infoFromFile[2] );
                    Console.println( ord );
                } else {
                    ord = Input.getScanner().nextLong();

                    }
                } catch ( InputMismatchException e ) {
                    Console.printError("Введен неверный тип данных.");
                }
        } while (ord == null);
        return ord;
    }

    /**
     * Ask question and get coordinate's dragon from input (cannot be null)
     * Contains 2 methods: absAbs() & askOrd()
     * @return coordinate's dragon
     */
    public static Coordinates askCoordinates() {
        Console.println( "Введите координаты дракона:" );
        return new Coordinates( askAbs(), askOrd() );
    }

    /**
     * Ask question and get age's dragon from input (must be >0)
     * @return age's dragon
     */
    public static int askAge() {
        Integer age = null;
        Console.println( "Введите возраст дракона (лет):" );
        do {
            try {
                Console.ps2();

                if ( Input.isFileMode() ){
                    age = Integer.valueOf( infoFromFile[3] );
                    Console.println( age );
                } else {
                    age = Input.getScanner().nextInt();
                    if (age <= 0) throw new IllegalArgumentException();
                }
            } catch ( InputMismatchException e ) {
                Console.printError("Введен неверный тип данных.");
            } catch (IllegalArgumentException e ) {
                Console.printError("Возраст должен быть больше 0");
                age = null;
            }
        } while (age == null);
        return age;
    }

    /**
     * Ask question and get weight's dragon from input (must be >0; cannot be null)
     * @return weight's dragon
     */
    public static long aksWeight() {
        Long weight = null;
        Console.println("Введите вес дракона (кг):");
        do {
            try {
                Console.ps2();

                if ( Input.isFileMode() ) {
                    weight = Long.valueOf( infoFromFile[4] );
                    Console.println( weight );
                } else {
                    weight = Input.getScanner().nextLong();
                    if ( weight <= 0 ) throw new IllegalArgumentException();
                }
            } catch ( InputMismatchException e ) {
                Console.printError( "Введен неверный тип данных." );
            } catch ( IllegalArgumentException e ) {
                Console.printError( "Вес должен быть больше 0." );
                weight = null;
            }
        } while ( weight == null );
        return weight;
    }

    /**
     * Ask question and know about ability's dragon to speak from input
     * @return can (or cannot) speak
     */
    public static Boolean askSpeaking() {
        String canSpeak = null;
        Console.println( "Может ли дракон говорить? (true или false)" );
        do {
            try {
                Console.ps2();

                if ( Input.isFileMode() ) {
                    canSpeak = infoFromFile[5];
                    Console.println( canSpeak );
                } else {
                    canSpeak = Input.getScanner().nextLine().toLowerCase();
                }
                if ( !canSpeak.equals("true") && !canSpeak.equals("false") ) throw new IllegalArgumentException();
            } catch ( IllegalArgumentException e ) {
                Console.printError( "Введен неверный тип данных." );
            } catch ( NullPointerException e ) {
                Console.println("");
            }
        } while ( !canSpeak.equals("true") && !canSpeak.equals("false"));
        return canSpeak.equals("true");
    }

    /**
     * Ask question and get color's dragon from input (cannot be null)
     * @return color of dragon
     */
    public static Color askColor (){
        String strColor;
        Color color = null;
        Console.println("Какого цвета дракон? (green/yellow/white)");
        do {
            try{
                Console.ps2();

                if ( Input.isFileMode() ){
                    strColor = infoFromFile[6];
                    Console.println( strColor );
                } else {
                    strColor = Input.getScanner().nextLine();
                }
                color = Color.valueOf( strColor.toUpperCase() );
            } catch ( IllegalArgumentException e ){
                Console.printError("Недопустимый такой цвет.");
            }
        } while ( color == null );
        return color;
    }

    /**
     * Ask question and get number of eye's dragon from input, after that make head's dragon
     * @return head's dragon
     */
    public static DragonHead askDragonHead() {
        Long eyes = null;
        Console.println("Сколько глаз у дракона?");
        do {
            try {
                Console.ps2();

                if (Input.isFileMode()) {
                    eyes = Long.valueOf(infoFromFile[7]);
                    Console.println(eyes);
                } else {
                    eyes = Input.getScanner().nextLong();
                    if (eyes <= 0) throw new IllegalArgumentException();
                }
            } catch ( InputMismatchException e ) {
                Console.printError("Введен неверный тип данных.");
            } catch ( IllegalArgumentException e ) {
                Console.printError("Количество глаз должно быть больше 0.");
                eyes = null;
            }
        } while ( eyes == null );
        return new DragonHead(eyes);
    }

    /**
     * Make dragon from the above information
     * @return dragon
     */
    public static Dragon buildDragon (){
        return new Dragon(
                askId(),
                askName(),
                askCoordinates(),
                askAge(),
                aksWeight(),
                askSpeaking(),
                askColor(),
                askDragonHead()
        );
    }

}
