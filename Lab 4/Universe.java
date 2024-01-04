import core.*;
import utility.*;

public class Universe {
    public static void main (String []args)  {
        Landforms cave = new Landforms("Cave", new Coordinate(0, 0));
        Rocket rocket = new Rocket("ITMO-Rocket", new Coordinate(200, 0), 100000, 100);
        Landforms pyramidMountain = new Landforms("Pyramid Mountain", new Coordinate(400, 0));

        Planet Moon = new Planet("Moon",  10);
        Planet Earth = new Planet("Earth", 50);

        Engineer Klepka = new Engineer("Klepka", Moon, 70);
        Engineer Zvezdochkin = new Engineer("Zvezdochkin", Moon, 60);
        Engineer Znayka = new Engineer("Znayka", Moon, 120);
        Astronaut littleGuys = new Astronaut("Little guys", Moon, 40);

        ZeroGravityDevice device = new ZeroGravityDevice("Zero-gravity-device", 100);

        System.out.println("Our story started !");

        Znayka.atPlace(cave);
        littleGuys.atPlace(rocket);
        Znayka.notice("his task was fulfilled.");
        rocket.descend();
        rocket.launch();
        Znayka.control(rocket);
        Znayka.think(littleGuys.getName() + " were planning to make fun of him.");
        Znayka.shout();
        Znayka.move();
        Znayka.atPlace(rocket);
        Znayka.checkZeroGravityDevice(device, rocket);
        Znayka.grab(device);
        Znayka.getOut(rocket);
        Klepka.getOut(rocket);
        Zvezdochkin.getOut(rocket);
        Klepka.chase(Znayka);
        Zvezdochkin.chase(Znayka);
        Znayka.runAndStop(rocket);
        Znayka.enableZeroGravityDevice(device, rocket);
        Klepka.soarUp();
        Zvezdochkin.soarUp();
        Znayka.notice("the fantastic jump.");
        Znayka.disableZeroGravityDevice(device, rocket);
        Klepka.flyDown();
        Klepka.stretchOut();
        Zvezdochkin.flyDown();
        Zvezdochkin.stretchOut();
        Earth.maxHeight(Klepka);
        Moon.maxHeight(Klepka);
        littleGuys.getOut(rocket);
        littleGuys.waited();
        Znayka.move();
        Znayka.atPlace(pyramidMountain);
        Znayka.enableZeroGravityDevice(device, rocket);
        Zvezdochkin.jumpUpTo(Znayka);
        Zvezdochkin.hugg(Znayka);
        Zvezdochkin.listen(Znayka);

        System.out.println("Our story closed !");
    }
}