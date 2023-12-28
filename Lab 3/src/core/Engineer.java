package core;

import utility.IEngineer;
import utility.PROFESSION;
import utility.STATUS_OF_WEIGHT;

import java.util.Objects;

public class Engineer extends Person implements IEngineer {
    private final PROFESSION profession = PROFESSION.ENGINEER;
    public Engineer (String name, Planet planet, double weight, Rocket rocket){
        super(name,planet,weight,rocket);
    }
    public Engineer (String name, Coordinate coordinate, Planet planet, double weight, Rocket rocket){
        super(name, coordinate,planet,weight,rocket);
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

    @Override
    public boolean equals(Object otherObject){
        if (!super.equals(otherObject)) return false;
        Engineer other = (Engineer) otherObject;
        return profession == other.profession;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 17 * Objects.hash(profession);
    }

    @Override
    public String toString() {
        return super.toString() + "[profession=" + profession + "]";
    }

}
