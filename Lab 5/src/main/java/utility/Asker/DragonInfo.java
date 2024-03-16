package utility.Asker;
import Data.*;
import utility.Console;

import java.util.*;

// this is class for asking user about the properties of dragon
public class DragonInfo {
    public static Set<Long> idSet = new HashSet<>();
    private static String[] fileScanner;

    public static boolean checkId (long id){
        return idSet.contains(id);
    }
    public static void getFileScanner (String[] str){
        fileScanner = str;
    }
    public static long askId(){
        long id;
        do {
//            id = askUser("Что такое идентификатор дракона?").nextLong();
            Random random = new Random();
            id = random.nextLong();
        } while ( id <= 0 || checkId(id) );
        return id;
    }
    //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    public static String askName (){
        String name;
        do {
            Console.println("Как зовут дракона?");
            Console.ps2();
            if (Asker.isFileMode()){
                name = fileScanner[0];
                Console.println(name);
            } else {
                name = Asker.getScanner().nextLine();
            }
        } while ( name == null || name.isEmpty() );
        return name;
    }
    //Поле не может быть null, Строка не может быть пустой
    public static Integer askAbs (){
        Integer abs = null;
        do {
            Console.println("+Абсцисса дракона:");
            Console.ps2();
            if (Asker.isFileMode()){
                abs = Integer.valueOf(fileScanner[1]);
                Console.println(abs);
            } else {
                if (Asker.getScanner().hasNextInt()){
                    abs = Asker.getScanner().nextInt();
                } else {
                    Console.printError("Введен неверный тип данных.");
                    Asker.getScanner().next();
                }
                abs = Asker.getScanner().nextInt();
            }
        } while (abs == null);
        return abs;
    }
    public static Long askOrd (){
        Long ord = null;
        do {
            Console.println("+Ордината дракона:");
            Console.ps2();
            if (Asker.isFileMode()){
                ord = Long.valueOf(fileScanner[2]);
                Console.println(ord);
            } else {
                if (Asker.getScanner().hasNextLong()){
                    ord = Asker.getScanner().nextLong();
                } else {
                    Console.printError("Введен неверный тип данных.");
                    Asker.getScanner().next();
                }
                ord = Asker.getScanner().nextLong();
            }
        } while (ord == null);
        return ord;
    }
    public static Coordinates askCoordinates (){
        Console.println("Введите координаты дракона:");
        return new Coordinates(askAbs(), askOrd());
    }
    //Поле не может быть null

    public static int askAge (){
        Integer age = null;
        do {
            Console.println("Введите возраст дракона (лет):");
            Console.ps2();
            if (Asker.isFileMode()){
                age = Integer.valueOf(fileScanner[3]);
                Console.println(age);
            } else {
                if (Asker.getScanner().hasNextInt()){
                    age = Asker.getScanner().nextInt();
                    if (age <= 0){
                        Console.printError("Возраст должен быть больше 0");
                        age = null;
                    }
                } else {
                    Console.printError("Введен неверный тип данных.");
                    Asker.getScanner().next();
                }
                age = Asker.getScanner().nextInt();
            }
        } while (age == null);
        return age;
    }
    //Значение поля должно быть больше 0
    public static long aksWeight (){
        Long weight = null;
        do {
            Console.println("Введите вес дракона (кг):");
            Console.ps2();
            if (Asker.isFileMode()){
                weight = Long.valueOf(fileScanner[4]);
                Console.println(weight);
            } else {
                if (Asker.getScanner().hasNextLong()){
                    weight = Asker.getScanner().nextLong();
                    if (weight <= 0){
                        Console.printError("Вес должен быть больше 0.");
                        weight = null;
                    }
                } else {
                    Console.printError("Введен неверный тип данных.");
                    Asker.getScanner().next();
                }
                weight = Asker.getScanner().nextLong();
            }
        } while (weight == null);
        return weight;
    }
    //Значение поля должно быть больше 0, Поле может быть null
    public static Boolean askSpeaking (){
        String valueAbility = null;
//        Sử dụng kiểu dữ liệu Boolean thay vì boolean: Kiểu dữ liệu Boolean có thể nhận giá trị null, trong
//        khi boolean không thể. Điều này cho phép bạn sử dụng null để biểu diễn trạng thái không xác định hoặc
//        chưa được khởi tạo.
        do{
            try{
                Console.println("Может ли дракон говорить? (true или false) ");
                Console.ps2();
                if (Asker.isFileMode()) {
                    valueAbility = fileScanner[5];
                    Console.println(valueAbility);
                } else {
                    do {
                        valueAbility = Asker.getScanner().nextLine();
                    } while (valueAbility.isEmpty());
                }
                valueAbility = valueAbility.toLowerCase();

                if (!valueAbility.equals("true") && !valueAbility.equals("false")) throw new IllegalArgumentException();
//                Đúng, trong Java, khi một ngoại lệ được ném bên trong khối try, luồng điều khiển sẽ chuyển đến khối catch
//                tương ứng để xử lý ngoại lệ. Trong trường hợp của bạn, khi một ngoại lệ IllegalArgumentException được ném
//                trong khối try, chương trình sẽ dừng lại và điều khiển được chuyển đến khối catch tương ứng với ngoại lệ này.
//                Điều này có nghĩa là đoạn mã trong khối catch sẽ được thực thi.
            } catch (IllegalArgumentException exception){
                Console.printError("Введен неверный тип данных.");
            }
        } while (!valueAbility.equals("true") && !valueAbility.equals("false"));
        return valueAbility.equals("true");
    }
    public static Color askColor (){
        Color color = null;
        do {
            Console.println("Какого цвета дракон? (green, yellow, white)");
            try{
                Console.ps2();
                Scanner scanner = Asker.getScanner();
                if (Asker.isFileMode()){
                    color = Color.valueOf(fileScanner[6].toUpperCase());
                    Console.println(fileScanner[6]);
                } else {
                    String strColor;
                    do {
                        strColor = scanner.nextLine();
                    }
                    while (strColor.isEmpty());
                    color = Color.valueOf(strColor.toUpperCase());
                }

            } catch (IllegalArgumentException exception){
                Console.printError("Недопустимый такой цвет.");
            }
            //IllegalArgumentException là một loại ngoại lệ trong Java được ném khi một phương thức nhận một đối số không hợp lệ.
        } while (color == null);
        return color;
    }
    //Поле может быть null

    public static DragonHead askDragonHead (){
        Long eyes = null;
        do {
            Console.println("Сколько глаз у дракона?");
            Console.ps2();

            Scanner scanner = Asker.getScanner();
            if (Asker.isFileMode()){
                eyes = Long.valueOf(fileScanner[7]);
                Console.println(eyes);
            } else {
                if (scanner.hasNextLong()){
                    eyes = scanner.nextLong();
                    if (eyes <= 0){
                        Console.printError("Количество глаз должно быть больше 0.");
                        eyes = null;
                    }
                } else {
                    Console.printError("Введен неверный тип данных.");
                    scanner.next();
                }
            }
        }  while (eyes==null);
        return new DragonHead(eyes);
    }

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
