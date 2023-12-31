package core;

import utility.IEngineer;
import utility.PROFESSION;
import utility.Place;
import utility.STATUS_OF_WEIGHT;

import java.util.Objects;

public class Engineer extends Person implements IEngineer {
    private final PROFESSION profession = PROFESSION.ENGINEER;

    public Engineer(String name, Planet planet, double weight) {
        super(name, planet, weight);
    }

    public Engineer(String name, Coordinate coordinate, Planet planet, double weight) {
        super(name, coordinate, planet, weight);
    }

    @Override
    public void enableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        System.out.printf("%s turned on the zero-gravity-device.\n", this.getName());
        if ( this.coordinate.equals(new Coordinate(100.0, 0.0)) ){
            zeroGravityDevice.turnOn();
            System.out.println(STATUS_OF_WEIGHT.WEIGHTLESS.getDescription());
        }
        else System.out.println("The device did not work.");
    }

    @Override
    public void disableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        System.out.printf("%s turned off the zero-gravity-device.\n", this.getName());
        if ( this.coordinate.equals(new Coordinate(100.0, 0.0)) ){
            zeroGravityDevice.turnOff();
            System.out.println(STATUS_OF_WEIGHT.WEIGHTED.getDescription());
        }
        else System.out.println("The device did not work.");
    }

    @Override
    public boolean equals(Object otherObject) {
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


