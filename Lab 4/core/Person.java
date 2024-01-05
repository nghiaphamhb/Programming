package core;

import utility.*;

import java.util.Objects;

public abstract class Person implements IPerson, IPersonFly, IPersonRun {
    protected final int fixedDistance = 100;
    protected final int lengthOfStep = 10;
    protected String name;
    protected Coordinate coordinate;
    protected Planet planet;
    protected double weight;
    protected Status statusOfWeight;
    public Person (String name, Planet planet, double weight){
        this.name = name;
        this.planet = planet;
        this.weight = weight;
        this.statusOfWeight = Status.WEIGHTED;
    }
    public Person (String name, Coordinate coordinate, Planet planet, double weight){
        this.name = name;
        this.coordinate = coordinate;
        this.planet = planet;
        this.weight = weight;
        this.statusOfWeight = Status.WEIGHTED;
    }

    public int getFixedDistance() {
        return fixedDistance;
    }

    public int getLengthOfStep() {
        return lengthOfStep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Status getStatusOfWeight() {
        return statusOfWeight;
    }

    public void setStatusOfWeight(Status statusOfWeight) {
        this.statusOfWeight = statusOfWeight;
    }
    @CustomLogging("This method represents the moving to a place action.")

    @Override
    public void moveToPlace (String namePlace, Coordinate coordinate){
        Place place = new Place(namePlace, coordinate) {     //anonymous class
            @Override
            public void printLocationMarker (){
                System.out.println("Location marker: " + namePlace + coordinate.toString() );
            }
        };
        place.printLocationMarker();
        move();
        setCoordinate(coordinate);
        System.out.println(Person.this.name + " moved to the " + namePlace);
    }
    @CustomLogging("This method represents the getting out action.")
    @Override
    public void getOut(Rocket rocket) {
        System.out.printf("%s got out of the %s.\n", this.name, rocket.getName());
    }
    @CustomLogging("This method represents the noticing action.")
    @Override
    public void notice(String what) {
        System.out.println(this.name + " noticed, that " + what );
    }
    @CustomLogging("This method represents the stretching out action.")
    @Override
    public void stretchOut() {
        System.out.printf(getName()+ " stretched out on the surface.\n");
    }
    @CustomLogging("This method represents the thinking action.")
    @Override
    public void think(String what) {
        System.out.println(name + " thougt that " + what);
    }
    @CustomLogging("This method represents the shouting action.")
    @Override
    public void shout() {
        System.out.println(name + " shouted angrily. " );
    }
    @CustomLogging("This method represents the soaring up action.")
    @Override
    public void soarUp() {
        System.out.printf("%s soared up.\n", this.name);
    }
    @CustomLogging("This method represents the flying down action.")
    @Override
    public void flyDown() {
        System.out.printf("%s flied down.\n", this.name);
    }
    @CustomLogging("This method represents the running and stopping actions.")
    @Override
    public void runAndStop(Rocket rocket) {
        int i = 1;
        while (Coordinate.distance(getCoordinate(), rocket.getCoord()) < fixedDistance) {
            setCoordinate( new Coordinate(getCoordinate().getAbs() + lengthOfStep, 0));
            IPersonRun runner = (coord, steps) -> {             //lambda expression
                System.out.printf("%s runned %d steps.\n", getName(), steps);
                setCoordinate(coord);
            };
            runner.run(getCoordinate(), lengthOfStep * i);
            i += 1;
        }
        stop();
    }
    @CustomLogging("This method represents the running action.")
    @Override
    public void run(Coordinate coord, int steps) {
        System.out.printf("%s runned %d steps.\n",name, steps);
        this.coordinate = coord;
    }
    @CustomLogging("This method represents the stopping action.")
    @Override
    public void stop() {
        System.out.println(this.name + " stoped. ");
    }
    @CustomLogging("This method represents the chasing action.")
    @Override
    public void chase(Person person) {
        System.out.printf("%s chased after %s.\n", this.name, person.getName());
    }
    @CustomLogging("This method represents the moving action.")
    @Override
    public void move() {
        System.out.println(name + " was moving." );
    }
    @CustomLogging("This method represents the grabbing action.")
    @Override
    public void grab(ZeroGravityDevice device) {
        System.out.println(name + " grabbed the " + device.getName());
    }
    @CustomLogging("This method represents the waiting action.")
    @Override
    public void waited(){
        System.out.println(name + " waited.");
    }
    @CustomLogging("This method represents the listenning action.")
    @Override
    public void listen(Engineer engineer) {
        System.out.println(name + " had finished listening to " + engineer.getName());
    }
    @CustomLogging("This method represents the hugging action.")
    @Override
    public void hugg(Engineer engineer) {
        System.out.println(name + " hugged " + engineer.getName());
    }
    @CustomLogging("This method represents the jumping-to action.")
    @Override
    public void jumpUpTo(Engineer engineer) {
        System.out.println(name + " jumped up to " + engineer.getName());
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


}
