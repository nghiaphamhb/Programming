package core;

import utility.SttOfFalling;

import java.util.Objects;

public class Planet {
    private String name;
    private double gravity;
    private SttOfFalling statusOfFalling;

    public Planet (String name, double gravity){
        this. name = name;
        this.gravity = gravity;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setGravity (double gravity){
        this.gravity = gravity;
    }
    public void setStatusOfFalling (SttOfFalling statusOfFalling){
        this.statusOfFalling = statusOfFalling;
    }

    public String getName (){
        return this.name;
    }
    public double getGravity(){
        return this.gravity;
    }
    public SttOfFalling getStatusFalling(){
        return this.statusOfFalling;
    }

    public void  maxHeight (Engineer engineer){
        double height = engineer.weight/(2*this.gravity);
        System.out.printf("The maximum height %s achieved on %s is %s m.\n", engineer.getName(), this.getName(), height);
        if (height >= 3.0){
            System.out.println(SttOfFalling.SAFE_WITH_SLIGHT_FRIGHT.getDescription());
        }else System.out.println(SttOfFalling.CRIPPLED.getDescription());
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Planet planet = (Planet) otherObject;
        return Objects.equals(name, planet.name)  &&
                Double.compare(planet.gravity, gravity) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * Double.hashCode(gravity);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", gravity=" + gravity +
                ", statusOfFalling=" + statusOfFalling +
                '}';
    }
}
