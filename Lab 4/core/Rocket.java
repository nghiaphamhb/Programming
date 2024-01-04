package core;

import utility.Place;

import java.util.Objects;

public class Rocket extends Place {
    private RocketParameters rocketParameters;

    public Rocket(String name, Coordinate coord, int fuelCapacity, int payloadCapacity) {
        super(name, coord);
        this.rocketParameters = new RocketParameters(fuelCapacity, payloadCapacity);
    }

    public RocketParameters getRocketParameters() {
        return rocketParameters;
    }

    public void setRocketParameters(RocketParameters rocketParameters) {
        this.rocketParameters = rocketParameters;
    }

    public void descend (){
        System.out.println( getName() + " descended again.");
    }

    public void launch() {
        // Local inner class to represent the LaunchController
        class LaunchController {
            private int launchCountdown = 3;
            final int minFuelCapacity = 20000;

            public void initiateLaunch() {
                // Check if fuel capacity is greater than minimum
                if (rocketParameters.getFuelCapacity() > minFuelCapacity && rocketParameters.getPayloadCapacity() < 1000) {
                    System.out.println("Launch sequence initiated. Ignition in:");

                    // Countdown loop
                    while (launchCountdown > 0) {
                        System.out.println(launchCountdown + "...");
                        launchCountdown--;

                        try {
                            // Pause for 1 second (simulating time passing)
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Ignition
                    System.out.println("Ignition!");
                } else {
                    System.out.println("Could not launch the "+ getName()+ ". Insufficient fuel capacity or overload.");
                }
            }
        }
        // Create an instance of the local inner class and initiate the launch
        LaunchController launchController = new LaunchController();
        launchController.initiateLaunch();
    }


    class RocketParameters {
        private int fuelCapacity;
        private int payloadCapacity;

        public RocketParameters(int fuelCapacity, int payloadCapacity) {
            this.fuelCapacity = fuelCapacity;
            this.payloadCapacity = payloadCapacity;
        }

        public int getFuelCapacity() {
            return fuelCapacity;
        }

        public void setFuelCapacity(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }

        public int getPayloadCapacity() {
            return payloadCapacity;
        }

        public void setPayloadCapacity(int payloadCapacity) {
            this.payloadCapacity = payloadCapacity;
        }
    }
    public void move(Coordinate coord) throws RocketException{
        if (coord == null) {
            throw new RocketException(this.name + " could not move at all.");
        }
        else setCoord(coord);

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Rocket rocket)) return false;
        if (!super.equals(object)) return false;
        return Objects.equals(getRocketParameters(), rocket.getRocketParameters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRocketParameters());
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "rocketParameters=" + rocketParameters +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                '}';
    }
}
