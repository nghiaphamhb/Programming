package utility;

import core.ZeroGravityDevice;

import java.util.Objects;

public abstract class Device {
    protected String name;
    protected STATUS_OF_DEVICE statusOfDv;
    public Device(String name, STATUS_OF_DEVICE status){
        this.name = name;
        this.statusOfDv = status;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setStatusOfDv (STATUS_OF_DEVICE statusOfDv){
        this.statusOfDv = statusOfDv;
    }

    public String getName(){
        return this.name;
    }
    public STATUS_OF_DEVICE getStatus(){
        return this.statusOfDv;
    }
    public void turnOn (){
        this.statusOfDv = STATUS_OF_DEVICE.ON;
        System.out.println(name + STATUS_OF_DEVICE.ON.getDescription());
    }
    public void turnOff (){
        this.statusOfDv = STATUS_OF_DEVICE.OFF;
        System.out.println(name + STATUS_OF_DEVICE.OFF.getDescription());
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + "\n" +
                ", state of device=" + statusOfDv +
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
        Device device = (Device) obj;
        return Objects.equals(name, device.name);
    }
}
