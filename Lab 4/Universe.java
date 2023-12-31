import core.*;
import utility.*;

public class Universe {
    public static void main (String []args) throws RocketMoveException {
        Landforms cave = new Landforms("Cave", new Coordinate(0, 0));
        Rocket rocket = new Rocket("Rocket", new Coordinate(200, 0));
        Landforms pyramidMountain = new Landforms("Pyramid Mountain", new Coordinate(300, 0));

        Planet Moon = new Planet("Moon",  10);
        Planet Earth = new Planet("Earth", 50);

        Engineer Klepka = new Engineer("Klepka", Moon, 70);
        Engineer Zvezdochkin = new Engineer("Zvezdochkin", Moon, 60);
        Engineer Znayka = new Engineer("Znayka", Moon, 120);
        Astronaut littleGuys = new Astronaut("Little guys", Moon, 40);

        ZeroGravityDevice device = new ZeroGravityDevice("Zero-gravity-device", STATUS_OF_DEVICE.OFF);

        System.out.println("Story started !");

        Znayka.atPlace(cave);
        littleGuys.atPlace(rocket);
        Znayka.notice("his task was fulfilled.");

        rocket.descend();
        try{
            rocket.pushOff(500);
        } catch (RocketPushOffException e){
            System.out.println(e.getMessage());
        }

        try{
            rocket.move(null);
        } catch (RocketMoveException e){
            System.out.println(e.getMessage());
        }

        Znayka.think(littleGuys.getName() + " were planning to make fun of him.");
        Znayka.shout();
        Znayka.move(rocket);
        Znayka.enableZeroGravityDevice(device);

        Znayka.grab(device);
        Znayka.goOut(rocket);
        Klepka.goOut(rocket);
        Klepka.chase(Znayka);
        Zvezdochkin.goOut(rocket);
        Zvezdochkin.chase(Znayka);

        Znayka.run(rocket.getCoord(), -100);
        Znayka.stop();
        Znayka.enableZeroGravityDevice(device);
        Klepka.soarUp();
        Zvezdochkin.soarUp();

        Znayka.notice("the fantastic jump.");
        Znayka.disableZeroGravityDevice(device);

        Klepka.flyDown();
        Klepka.stretchOut(Moon);
        Zvezdochkin.flyDown();
        Zvezdochkin.stretchOut(Moon);

        Earth.maxHeight(Klepka);
        Moon.maxHeight(Klepka);

        littleGuys.goOut(rocket);
        littleGuys.waited();

        Znayka.move(pyramidMountain);
        Znayka.enableZeroGravityDevice(device);

        Zvezdochkin.listen(Znayka);
        Zvezdochkin.jumpUpTo(Znayka);
        Zvezdochkin.hugg(Znayka);

        System.out.println("Story closed !");
    }
}
