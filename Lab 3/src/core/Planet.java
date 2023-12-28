package core;

import utility.STATUS_OF_FALLING;

import java.util.Objects;

public class Planet {
    private String name;
    private double gravity;
    private STATUS_OF_FALLING statusOfFalling;

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
    public void setStatusOfFalling (STATUS_OF_FALLING statusOfFalling){
        this.statusOfFalling = statusOfFalling;
    }

    public String getName (){
        return this.name;
    }
    public double getGravity(){
        return this.gravity;
    }
    public STATUS_OF_FALLING getStatusFalling(){
        return this.statusOfFalling;
    }

    public void  maxHeight (Engineer engineer){
        double height = 0.1 * engineer.weight + 0.6 * this.gravity;
        System.out.printf("The maximum height %s achieved on %s is %s m.\n", engineer.getName(), this.getName(), height);
        if (height <= 30){
            System.out.println(STATUS_OF_FALLING.SAFE_WITH_SLIGHT_FRIGHT.getDescription());
        }else System.out.println(STATUS_OF_FALLING.CRIPPLED.getDescription());

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
