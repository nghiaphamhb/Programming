package utility;
// class dùng để hiển thi tương tác lên terminal
public class Console {
    private static final String PS1 = "$ "; //đặt dấu nhắc cho dòng lệnh
    private static final String PS2 = "> "; //dành cho lệnh có nhiều dòng
//    Lệnh PS (Process Status) trong hệ điều hành UNIX và UNIX-like (bao gồm Linux) được sử dụng
//    để hiển thị trạng thái của các tiến trình đang chạy trên hệ thống

    public static void print(Object obj) {  //cho obj.toString() vao console
        System.out.print(obj);
    }  //gọi toString() của obj


    public static void println(Object obj) { //cho obj.toString() vao console
        System.out.println(obj);
    }

    public static void printError(Object obj) { //cho loi obj.toString() vao console
        System.out.println("Ошибка: " + obj);
    }

    public static void printTable(Object elementLeft, Object elementRight) {  // cho vao bang co 2 cot tro len
        System.out.printf(" %-35s %-1s %n", elementLeft, elementRight);
    } //- căn lề trái, 35(1) là số kí tự biểu diễn

    public static void ps1() { //in PS1 bang console
        print(PS1);
    }

    public static void ps2() { //in PS2 bang console
        print(PS2);
    }

    public static String getPS1() {
        return PS1;
    }

    public static String getPS2() {
        return PS2;
    }

}
