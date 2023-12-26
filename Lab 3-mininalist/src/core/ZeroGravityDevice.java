package core;

import utility.STATUS_OF_DEVICE;
import utility.STATUS_OF_WEIGHT;

public class ZeroGravityDevice {
    private STATUS_OF_DEVICE statusOfDv;
    private String name;
    public ZeroGravityDevice(STATUS_OF_DEVICE status, String name){
        this.statusOfDv = status;
        this.name = name;
    }
    public void turnOn (){
        this.statusOfDv = STATUS_OF_DEVICE.ON;
        System.out.println(STATUS_OF_DEVICE.ON.getDescription());
    }
    public void turnOff (){
        this.statusOfDv = STATUS_OF_DEVICE.OFF;
        System.out.println(STATUS_OF_DEVICE.OFF.getDescription());
    }
    public STATUS_OF_DEVICE getStatus(){
        return this.statusOfDv;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public int hashCode(){
        return 1;
    }
    public String toString(){
        return "";
    }
}
