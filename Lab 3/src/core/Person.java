package core;

import utility.*;

import java.util.Objects;

public class Person implements IPerson, IPersonFly, IPersonRun{
    protected String name;
    protected Coordinate coordinate;
    protected Planet planet;
    protected double weight;
    protected Rocket rocket;
    protected STATUS_OF_WEIGHT statusOfWeight;
    public Person (String name, Planet planet, double weight, Rocket rocket){
        this.name = name;
        this.planet = planet;
        this.weight = weight;
        this.rocket = rocket;
        this.statusOfWeight = STATUS_OF_WEIGHT.WEIGHTED;
    }
    public Person (String name, Coordinate coordinate, Planet planet, double weight, Rocket rocket){
        this.name = name;
        this.coordinate = coordinate;
        this.planet = planet;
        this.weight = weight;
        this.rocket = rocket;
        this.statusOfWeight = STATUS_OF_WEIGHT.WEIGHTED;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setCoordinate (double abs, double ord){
        coordinate.setAbs(abs);
        coordinate.setOrd(ord);
    }
    public void setPlanet(Planet planet){
        this.planet = planet;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setRocket(Rocket rocket){
        this.rocket = rocket;
    }
    public void setStatusOfWeight ( STATUS_OF_WEIGHT statusOfWeight){
        this.statusOfWeight = statusOfWeight;
    }

    public String getName(){
        return name;
    }
    public Coordinate getCoord(){
        return coordinate;
    }
    public Planet getPlanet(){
        return planet;
    }
    public double getWeight(){
        return weight;
    }
    public String getRocket(){
        return rocket.getModel();
    }
    public STATUS_OF_WEIGHT getStatusOfWeight (){
        return statusOfWeight;
    }

    @Override
    public void jump(Rocket rocket) {
        System.out.printf("%s jumped out of the %s.\n", this.name, rocket.getModel());
    }
    @Override
    public void see(String what) {
        System.out.println(this.name + " saw " + what );
    }
    @Override
    public void stretchOut(Planet planet) {
        System.out.printf("%s stretched out on the surface of %s\n", this.name, planet.getName());
    }
    @Override
    public void soarUp() {
        System.out.printf("%s soared up.\n", this.name);
    }

    @Override
    public void flyDown() {
        System.out.printf("%s flied down.\n", this.name);
    }

    @Override
    public void run(Coordinate coordinate, int steps) {
        for (int i = 0; i <= steps; i+= 10){
            if (i == 0) System.out.println(this.name + " started running.");
            else System.out.printf(this.name + " runned %s steps.\n", i );
        }
    }
    @Override
    public void stop() {
        System.out.println(this.name + " stoped. ");
    }

    @Override
    public void chase(Person person) {
        System.out.printf("%s chased after %s.\n", this.name, person.getName());
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
                Double.compare(person.weight, weight) == 0 &&
                Objects.equals(rocket, person.rocket) ;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * Objects.hashCode(planet)
                + 13 * Double.hashCode(weight)
                + 15 * Objects.hashCode(rocket);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", abs=" + this.coordinate.getAbs() +
                ", ord=" + this.coordinate.getOrd() +
                ", planet=" + this.planet.getName() +
                ", weight=" + weight +
                ", rocket=" + this.rocket.getModel()+
                ", status of weight=" + statusOfWeight +
                '}';
    }
}
