package Client.Utility.Authentication;

import Client.Utility.Display;

import java.util.Scanner;

/**
 * Class for asking username and password
 */
public class AuthAsker{
    /**
     * ask username
     * @return username
     */
    public static String askUsername(){
        String login;
        while (true) {
            try{
                Display.println("Enter username: ");
                Display.ps2();
                login = scanner();
                if (login.isEmpty()) throw new Exception();
                break;
            } catch (Exception e){
                Display.printError("The login must not empty!");
            }
        }
        return login;
    }

    /**
     * Ask password
     * @return password
     */
    public static String askPassword(){
        String password;
        while (true) {
            try{
                Display.println("Enter password: ");
                Display.ps2();
                password = scanner();
                if (password.isEmpty()) throw new Exception();
                break;
            } catch (Exception e){
                Display.printError("The password must not empty!");
            }
        }
        return password;
    }

    /**
     * ask if user is registed
     * @return answer
     */
    public static boolean askIfRegistered(){
        String question = "Do you have an account? (\"Yes\" or \"No\")";
        String answer;
        while(true){
            try{
                Display.println(question);
                Display.ps2();
                answer = scanner().toLowerCase();
                if (!answer.equals("yes") && !answer.equals("no")) throw new Exception();
                if (answer.equals("no")) Display.println("Create new user:");
                break;
            } catch (Exception e) {
                Display.printError("The syntax is not correct !");
            }
        }
        return answer.equals("yes");
    }

    /**
     * Get string from input
     * @return string
     */
    private static String scanner(){
        return new Scanner(System.in).nextLine().trim();
    }


}
