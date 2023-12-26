package core;

import utility.STATUS_OF_FALLING;

public class Planet {
    private String name;
    private double gravity;
    private STATUS_OF_FALLING statusOfFalling;
    public Planet (String name, double gravity){
        this. name = name;
        this.gravity = gravity;
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public int hashCode(){
        return this.hashCode();
    }
    public String toString (){
        return "hello";
    }
}
