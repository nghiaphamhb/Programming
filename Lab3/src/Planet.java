public class Planet {
    private String name;
    private double speed;
    SttOfFalling statusOfFalling;
    public Planet(String name, double speed, SttOfFalling statusOfFalling){
        this.name = name;
        this.speed = speed ;
        this.statusOfFalling = statusOfFalling;
    }
    public String getName(){
        return name;
    }
    public double getSpeed(){
        return speed;
    }
    public SttOfFalling getSttOfFalling(){
        return statusOfFalling;
    }

}