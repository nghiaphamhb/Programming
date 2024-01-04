package utility;

import core.Rocket;
import core.ZeroGravityDevice;
import core.ZeroGravityDeviceException;

public interface IEngineer {
    void control (Rocket rocket);
    void enableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket);
    void disableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket);
    void checkZeroGravityDevice(ZeroGravityDevice zeroGravityDevice, Rocket rocket);
}
