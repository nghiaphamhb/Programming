package core;
import java.util.Objects;
import utility.*;

public class Person implements IPersonRun, IPerson, IPersonFly {
    protected String name;
    protected Coordinate coord;
    protected Planet planet;
    protected double speed;
    protected double weight;
    protected Rocket rocket;
    protected Status status;
    public Person (String name, double weight, Coordinate coord, Planet planet, Rocket rocket){
        this.name = name;
        this.weight = weight;
        this.coord = coord;
        this.planet = planet;
        this.speed = planet.getSpeed();
        this.status = Status.WEIGHTED;
        this.rocket = rocket;
    }
    public Person (String name,double weight, Planet planet, Rocket rocket){
        this.name = name;
        this.weight = weight;
        this.planet = planet;
        this.speed = planet.getSpeed();
        this.status = Status.WEIGHTED;
        this.rocket = rocket;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Coordinate getCoord(){
        return coord;
    }
    public Planet getPlanet(){
        return planet;
    }
    public void setPlanet(Planet planet){
        this.planet = planet;
    }
    public String getRocket(){
        return rocket.getModel();
    }
    public double getSpeed(){
        return this.speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public void setRocket(Rocket rocket){
        this.rocket = rocket;
    }
    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }



    @Override
    public void jump() {
        System.out.printf("%s is jumping out of the rocket\n", name);
    }

    @Override
    public void notice() {
        System.out.printf("%s is seeing the fantastic jump\n", name);
    }

    @Override
    public void stretchOut() {
        System.out.printf("%s is stretching out on the surface\n", name);
    }

    @Override
    public void soarUp() {
        System.out.printf("%s is soaring up\n", name);
    }

    @Override
    public void flyDown() {
        System.out.printf("%s is flying down\n", name);
    }
    @Override
    public void run(Coordinate coord, int steps) {
        System.out.printf("%s runned %d steps.\n",name, steps);
        this.coord = coord;

    }

    @Override
    public void stop() {
        System.out.printf("%s stop.\n",name);
    }

    @Override
    public void chase(Person person) {
        System.out.printf("%s is chasing after %s\n", this.name, person.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return name.equals(person.name) &&
                planet.equals(person.planet) &&
                Double.compare(person.speed, speed) == 0 &&
                Objects.equals(rocket, person.rocket) ;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * Objects.hashCode(planet)
                + 13 * Double.hashCode(speed)
                + 15 * Objects.hashCode(rocket);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", abs=" + this.coord.getAbs() +
                ", ord=" + this.coord.getOrd() +
                ", planet=" + this.planet.getName() +
                ", speed=" + speed +
                ", rocket=" + this.rocket.getModel()+
                ", status=" + status +
                '}';
    }
}