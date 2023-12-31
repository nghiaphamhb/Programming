package core;

import utility.*;

import java.util.Objects;

public abstract class Person implements IPerson, IPersonFly, IPersonRun{
    protected String name;
    protected Coordinate coordinate;
    protected Planet planet;
    protected double weight;
    protected STATUS_OF_WEIGHT statusOfWeight;
    public Person (String name, Planet planet, double weight){
        this.name = name;
        this.planet = planet;
        this.weight = weight;
        this.statusOfWeight = STATUS_OF_WEIGHT.WEIGHTED;
    }
    public Person (String name, Coordinate coordinate, Planet planet, double weight){
        this.name = name;
        this.coordinate = coordinate;
        this.planet = planet;
        this.weight = weight;
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
    public STATUS_OF_WEIGHT getStatusOfWeight (){
        return statusOfWeight;
    }
    @Override
    public void atPlace (Place place){   //local inner class and anonymous class
        this.coordinate = place.getCoord();
        class LocalInnerClass{
            void print (){
                System.out.println(Person.this.name + " was at the " + place.getName());
            }
        }
        LocalInnerClass localInnerClass = new LocalInnerClass();
        localInnerClass.print();
    }
    @Override
    public void goOut(Rocket rocket) {   //lambda expression
        Runnable lambdaexpression = () -> System.out.printf("%s went out of the %s.\n", this.name, rocket.getName());
        lambdaexpression.run();
    }
    @Override
    public void notice(String what) {
        System.out.println(this.name + " noticed, that " + what );
    }
    @Override
    public void stretchOut(Planet planet) {
        System.out.printf("%s stretched out on the surface of %s\n", this.name, planet.getName());
    }

    @Override
    public void think(String what) {
        System.out.println(name + " thougt that " + what);
    }

    @Override
    public void shout() {
        System.out.println(name + " shouted angrily. " );
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
        this.coordinate = new Coordinate(coordinate.getAbs() + steps, 0);
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
    public void move(Place place) {
        System.out.println(name + " moved to the " + place.getName());
        this.coordinate = place.getCoord();
    }
    @Override
    public void grab(ZeroGravityDevice device) {
        System.out.println(name + " grabbed the " + device.getName());
    }
    public void waited (){
        System.out.println(name + " waited.");
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
                Double.compare(person.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * Objects.hashCode(planet)
                + 13 * Double.hashCode(weight);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", abs=" + this.coordinate.getAbs() +
                ", ord=" + this.coordinate.getOrd() +
                ", planet=" + this.planet.getName() +
                ", weight=" + weight +
                ", status of weight=" + statusOfWeight +
                '}';
    }

    public void listen(Engineer engineer) {
        System.out.println(name + " listened to " + engineer.getName());
    }

    public void hugg(Engineer engineer) {
        System.out.println(name + " hugged " + engineer.getName());
    }

    public void jumpUpTo(Engineer engineer) {
        System.out.println(name + " jumped up to " + engineer.getName());
    }
}
