public class Engineer extends Person implements IEngineer{
    public Engineer(String name, Profession profession, Coordinate coord, Planet planet, Rocket rocket) {
        super(name, profession, coord, planet, rocket);
    }

    public Engineer(String name, Profession profession, Planet planet, Rocket rocket) {
        super(name, profession, planet, rocket);
    }

    @Override
    public void enableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        this.status = Status.WEIGHTLESS;
        zeroGravityDevice.turnOn(this);
    }

    @Override
    public void disableZeroGravityDevice(ZeroGravityDevice zeroGravityDevice) {
        this.status = Status.WEIGHTED;
        zeroGravityDevice.turnOff(this);
    }
}
