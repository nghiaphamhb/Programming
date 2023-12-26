package core;

import utility.IEngineer;
import utility.PROFESSION;
import utility.STATUS_OF_WEIGHT;

public class Engineer extends Person implements IEngineer {
    private STATUS_OF_WEIGHT statusOfWeight;
    public Engineer (String name, PROFESSION profession, Coordinate coordinate, Planet planet, double weight, Rocket rocket){
        this.name = name;
        this.profession = PROFESSION.ENGINEER;
        this.coordinate = coordinate;
        this.planet = planet;
        this.weight = weight;
        this.rocket = rocket;
    }
    public Engineer (String name, PROFESSION profession, Planet planet, double weight, Rocket rocket){
        this.name = name;
        this.profession = PROFESSION.ENGINEER;
        this.planet = planet;
        this.weight =weight;
        this.rocket = rocket;
    }
    @Override
    public void enableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        System.out.printf("%s turned on the zero-gravity-device.\n", this.getName());
        zeroGravityDevice.turnOn();
        System.out.println(STATUS_OF_WEIGHT.WEIGHTLESS.getDescription());
    }

    @Override
    public void disableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        System.out.printf("%s turned off the zero-gravity-device.\n", this.getName());
        zeroGravityDevice.turnOff();
        System.out.println(STATUS_OF_WEIGHT.WEIGHTED.getDescription());
    }
}
