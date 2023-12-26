import core.*;
import utility.*;

public class Universe {
    public static void main(String[] args) {
        final int fixedDistance = 100;
        final int lengthOfStep = 10;

        Planet moon = new Planet("Moon", 3679.2, 1.625);
        Planet earth = new Planet("Earth", 1.67,9.8);
        Planet mars = new Planet("Mars",5.66,3.7 );
        Coordinate coordRocket = new Coordinate(0, 0);
        Coordinate coordZnayka = new Coordinate(10, 0);
        Rocket itmo = new Rocket("ITMO", coordRocket);

        Engineer klepka = new Engineer("Klepka",70, moon, itmo);
        Engineer zvezdochkin = new Engineer("Zvezdochkin",2, earth, itmo);
        Engineer znayka = new Engineer("Znayka",66, coordZnayka, moon, itmo);

        ZeroGravityDevice itmoDevice = new ZeroGravityDevice("itmoDevice");

        znayka.jump();
        klepka.jump();
        zvezdochkin.jump();
        klepka.chase(znayka);
        zvezdochkin.chase(znayka);

        int i = 1;
        while ((Coordinate.distance(znayka.getCoord(), itmo.getCoord())) <= fixedDistance) {
            znayka.getCoord().setAbs(znayka.getCoord().getAbs() + lengthOfStep);
            znayka.getCoord().setOrd(0);
            znayka.run(znayka.getCoord(), 10 * i);
            i += 1;
        }

        znayka.stop();
        znayka.enableZeroGravityDevice(itmoDevice);
        System.out.println(Status.WEIGHTLESS.getDescription());

        klepka.soarUp();
        zvezdochkin.soarUp();
        znayka.notice();
        znayka.disableZeroGravityDevice(itmoDevice);
        System.out.println(Status.WEIGHTED.getDescription());

        klepka.flyDown();
        zvezdochkin.flyDown();
        klepka.stretchOut();
        zvezdochkin.stretchOut();
        System.out.printf("Percent of damage of %s when he is on %s is %f : \n", klepka.getName(), klepka.getPlanet().getName(), klepka.getPlanet().damage(klepka));
        System.out.println(klepka.getPlanet().getSttOfFalling().getDescription());
        System.out.printf("Percent of damage of %s when he is on %s is %f : \n", zvezdochkin.getName(), zvezdochkin.getPlanet().getName(), zvezdochkin.getPlanet().damage(zvezdochkin));
        System.out.println(zvezdochkin.getPlanet().getSttOfFalling().getDescription());
        klepka.setPlanet(mars);
        System.out.printf("Percent of damage of %s when he is on %s is %f : \n", klepka.getName(), klepka.getPlanet().getName(), klepka.getPlanet().damage(klepka));
        System.out.println(klepka.getPlanet().getSttOfFalling().getDescription());
    }
}