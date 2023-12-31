package core;

import utility.Device;
import utility.STATUS_OF_DEVICE;
import utility.STATUS_OF_WEIGHT;

import java.util.Objects;

public class ZeroGravityDevice extends Device {

    public ZeroGravityDevice(String name, STATUS_OF_DEVICE status) {
        super(name, status);
    }
    @Override
    public void turnOn (){
        super.turnOn();
        System.out.println("The gravity disappeared.");
    }
    @Override
    public void turnOff(){
        super.turnOff();
        System.out.println("The gravity existed.");
    }
}
