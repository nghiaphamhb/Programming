package core;

import utility.SttDevice;
import java.util.Objects;

public class ZeroGravityDevice {
    private String name;
    private SttDevice state;

    public ZeroGravityDevice(String name) {
        this.name = name;
        state = SttDevice.OFF;
    }

    public void turnOn(Person person) {
        state = SttDevice.ON;
        state.getDescription();
        person.setSpeed(0);
    }

    public void turnOff(Person person) {
        state = SttDevice.OFF;
        state.getDescription();
        person.setSpeed(person.planet.getSpeed());
    }

    public String getName() {
        return name;
    }

    public SttDevice getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ZeroGravityDevice{" +
                "name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public int hashCode() {
        return 20 * Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ZeroGravityDevice zeroGravityDevice = (ZeroGravityDevice) obj;
        return Objects.equals(name, zeroGravityDevice.name);
    }
}