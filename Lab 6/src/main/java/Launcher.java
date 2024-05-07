import Client.ClientApp;
import Server.SvApp;

public class Launcher {
    public static void main(String[] args) {
        try {
            String mainClass = args[0];

            if (!mainClass.equals("ClientApp") && !mainClass.equals("SvApp")) {
                throw new Exception();
            }

            switch (mainClass) {
                case "ClientApp":
                    if (args.length > 1) throw new Exception();
                    ClientApp.main(new String[]{});
                    break;
                case "SvApp":
                    String jsonFile = args[1];
                    SvApp.main(new String[]{jsonFile});
                    break;
                default:
                    System.out.println("Unknown main class: " + mainClass);
            }
        } catch (Exception e) {
            System.out.println("Usage: java -jar Lab_6.jar [ ClientApp / SvApp <jsonFile> ]");
            return;
        }
    }
}
