package core;

import utility.SttDevice;

import java.util.Objects;

public class ZeroGravityDevice {
    protected String name;
    protected SttDevice statusOfDv;
    protected double operatingRange;

    public ZeroGravityDevice(String name, double operatingRange) {
        this.name = name;
        this.statusOfDv = SttDevice.OFF;
        this.operatingRange = operatingRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SttDevice getStatusOfDv() {
        return statusOfDv;
    }

    public void setStatusOfDv(SttDevice statusOfDv) {
        this.statusOfDv = statusOfDv;
    }

    public double getOperatingRange() {
        return operatingRange;
    }

    public void setOperatingRange(double operatingRange) {
        this.operatingRange = operatingRange;
    }

    public void turnOn (Person person, Rocket rocket) throws ZeroGravityDeviceException {
        if (person == null) {
            throw new ZeroGravityDeviceException("Could not turn on the device for a null person.");
        }
        if (Coordinate.distance(person.getCoordinate(), rocket.getCoord()) != 100) {
            throw new ZeroGravityDeviceException(
                    person.getName() + " was not in the range of operation of the device. Device could not be activated.");
        }
        else {
            statusOfDv = SttDevice.ON;
            System.out.println(getStatusOfDv().getDescription());
            System.out.println("The gravity disappeared.");
        }
    }
    public void turnOff(Person person, Rocket rocket) throws ZeroGravityDeviceException {
        if (person == null) {
            throw new ZeroGravityDeviceException("Could not turn off the device for a null person.");
        }
        if (Coordinate.distance(person.getCoordinate(), rocket.getCoord()) != 100) {
            throw new ZeroGravityDeviceException(
                    person.getName() + " was not in the range of operation of the device. Device could not be activated.");
        }
        else {
            statusOfDv = SttDevice.OFF;
            System.out.println(getStatusOfDv().getDescription());
            System.out.println("The gravity appeared.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ZeroGravityDevice device)) return false;
        return Double.compare(getOperatingRange(), device.getOperatingRange()) == 0 && Objects.equals(getName(), device.getName()) && getStatusOfDv() == device.getStatusOfDv();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStatusOfDv(), getOperatingRange());
    }

    @Override
    public String toString() {
        return "ZeroGravityDevice{" +
                "name='" + name + '\'' +
                ", statusOfDv=" + statusOfDv +
                ", operatingRange=" + operatingRange +
                '}';
    }
}
