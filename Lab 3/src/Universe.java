import core.*;
import utility.STATUS_OF_DEVICE;
import utility.PROFESSION;

public class Universe {
    public static void main (String []args) {
        Planet Moon = new Planet("Moon",  10);
        Planet Earth = new Planet("Earth", 30);
        Planet Mars = new Planet("Mars", 50);

        Rocket ItmoRocket = new Rocket("ITMO-Rocket", new Coordinate(0, 0));

        Engineer Klepka = new Engineer("Klepka", Moon, 80, ItmoRocket);
        Engineer Zvezdochkin = new Engineer("Zvezdochkin", Moon, 60, ItmoRocket);
        Engineer Znayka = new Engineer("Znayka", Moon, 120, ItmoRocket);
        ZeroGravityDevice device = new ZeroGravityDevice("ITMO device", STATUS_OF_DEVICE.OFF);

        System.out.println("Story started !");

        Klepka.jump(ItmoRocket);
        Zvezdochkin.jump(ItmoRocket);
        Klepka.chase(Znayka);
        Zvezdochkin.chase(Znayka);

        Znayka.run(ItmoRocket.getCoord(), 100);
        Znayka.stop();
        Znayka.enableZeroGravityDevice(device);

        Klepka.soarUp();
        Zvezdochkin.soarUp();

        Znayka.see("the fantastic jump.");
        Znayka.disableZeroGravityDevice(device);

        Klepka.flyDown();
        Zvezdochkin.flyDown();
        Klepka.stretchOut(Moon);
        Zvezdochkin.stretchOut(Moon);

        Moon.maxHeight(Klepka);
        Earth.maxHeight(Zvezdochkin);
        Mars.maxHeight(Klepka);

        System.out.println("Story closed !");
    }
}
