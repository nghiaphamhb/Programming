package core;

import utility.IEngineer;
import utility.Profession;
import utility.Status;

import java.util.Objects;

public class Engineer extends Person implements IEngineer {
    private final Profession profession = Profession.ENGINEER;

    public Engineer(String name, Planet planet, double weight) {
        super(name, planet, weight);
    }

    public Engineer(String name, Coordinate coordinate, Planet planet, double weight) {
        super(name, coordinate, planet, weight);
    }

    @Override
    public void control(Rocket rocket) {
        System.out.println(getName() + " tried to control the " + rocket.getName());
        try{
            rocket.move(null);
        }catch (RocketException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void enableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket) {
        System.out.println(getName() + " tried to turn on the " + zeroGravityDevice.getName());

        try {
            zeroGravityDevice.turnOn(this, rocket);
            this.statusOfWeight = Status.WEIGHTLESS;
            System.out.println(this.statusOfWeight.getDescription());
        } catch (ZeroGravityDeviceException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void disableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket) {
        System.out.println(getName() + " tried to turn off the " + zeroGravityDevice.getName());
        try {
            zeroGravityDevice.turnOff(this, rocket);
            this.statusOfWeight = Status.WEIGHTED;
            System.out.println(this.statusOfWeight.getDescription());
        } catch (ZeroGravityDeviceException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void checkZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket){
        System.out.println(getName() + " started checking the " + zeroGravityDevice.getName() + ":");
        enableZeroGravityDevice(zeroGravityDevice, rocket);
        disableZeroGravityDevice(zeroGravityDevice, rocket);
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


