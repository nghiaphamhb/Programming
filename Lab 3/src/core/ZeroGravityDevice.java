package core;

import utility.STATUS_OF_DEVICE;
import utility.STATUS_OF_WEIGHT;

import java.util.Objects;

public class ZeroGravityDevice {
    private String name;
    private STATUS_OF_DEVICE statusOfDv;

    public ZeroGravityDevice(String name, STATUS_OF_DEVICE status){
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
        System.out.println(STATUS_OF_DEVICE.ON.getDescription());
    }
    public void turnOff (){
        this.statusOfDv = STATUS_OF_DEVICE.OFF;
        System.out.println(STATUS_OF_DEVICE.OFF.getDescription());
    }

    @Override
    public String toString() {
        return "ZeroGravityDevice{" +
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
        ZeroGravityDevice zeroGravityDevice = (ZeroGravityDevice) obj;
        return Objects.equals(name, zeroGravityDevice.name);
    }
}
