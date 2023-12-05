public class Universe {
    public static double distance (Coordinate coord1, Coordinate coord2){
        double distance = Math.sqrt(Math.pow((coord1.getAbs()-coord2.getAbs()),2)+
                Math.pow((coord1.getOrd()-coord2.getOrd()),2));
        return distance;
    }
    public static void main(String[] args) {
        Planet moon = new Planet("Moon", 3679.2, SttOfFalling.SAFE_WITH_SLIGHT_FRIGHT);
        Planet earth = new Planet("Earth", 1.67, SttOfFalling.CRIPPLED);
        Coordinate coordRocket = new Coordinate(0, 0);
        Coordinate coordZnayka = new Coordinate(10, 0);
        Rocket itmo = new Rocket("ITMO",coordRocket);
        Engineer Klepka = new Engineer("Klepka", Profession.ENGINEER, moon, itmo);
        Engineer Zvezdochkin = new Engineer("Zvezdochkin", Profession.ENGINEER, moon, itmo);
        Engineer Znayka = new Engineer("Znayka", Profession.ENGINEER, coordZnayka, moon, itmo);
        ZeroGravityDevice itmoDevice = new ZeroGravityDevice();
        Znayka.jump();
        Klepka.jump();
        Zvezdochkin.jump();
        Klepka.chase(Znayka);
        Zvezdochkin.chase(Znayka);
        int i = 1;
        while (distance(Znayka.coord, itmo.coord)<=100){
            Znayka.coord.setAbs(Znayka.getAbscissa() + 10);
            Znayka.coord.setOrd(0);
            Znayka.run(Znayka.coord, 10*i);
            i += 1;
        }
        Znayka.stop();
        Znayka.enableZeroGravityDevice(itmoDevice);
        System.out.println(Status.WEIGHTLESS.getDescription());
        Klepka.soarUp();
        Zvezdochkin.soarUp();
        Znayka.notice();
        Znayka.disableZeroGravityDevice(itmoDevice);
        System.out.println(Status.WEIGHTED.getDescription());
        Klepka.flyDown();
        Zvezdochkin.flyDown();
        Klepka.stretchOut();
        Zvezdochkin.stretchOut();
        System.out.println(Klepka.getPlanet().getSttOfFalling().getDescription());
        Klepka.setPlanet(earth);
        System.out.println(Klepka.getPlanet().getSttOfFalling().getDescription());
    }
}
