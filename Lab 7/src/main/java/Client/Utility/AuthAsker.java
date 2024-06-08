package Client.Utility;

import java.util.Scanner;

public class AuthAsker{
    public static String askUsername(){
        String login;
        while (true) {
            try{
                Display.println("Enter username: ");
                Display.ps2();
                login = scanner().nextLine().trim();
                if (login.isEmpty()) throw new Exception();
                break;
            } catch (Exception e){
                Display.printError("The login must not empty!");
            }
        }
        return login;
    }

    public static String askPassword(){
        String password;
        while (true) {
            try{
                Display.println("Enter password: ");
                Display.ps2();
                password = scanner().nextLine().trim();
                if (password.isEmpty()) throw new Exception();
                break;
            } catch (Exception e){
                Display.printError("The password must not empty!");
            }
        }
        return password;
    }

    public static boolean askIfRegistered(){
        String question = "Do you have an account? (\"Yes\" or \"No\")";
        String answer;
        while(true){
            try{
                Display.println(question);
                Display.ps2();
                answer = scanner().nextLine().trim().toLowerCase();
                if (!answer.equals("yes") && !answer.equals("no")) throw new Exception();
                if (answer.equals("no")) Display.println("Create new user:");
                break;
            } catch (Exception e) {
                Display.printError("The syntax is not correct !");
            }
        }
        return answer.equals("yes");
    }

    private static Scanner scanner(){
        return new Scanner(System.in);
    }


}
