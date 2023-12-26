package core;

import utility.*;

public class Person implements IPerson, IPersonFly, IPersonRun{
    protected String name;
    protected PROFESSION profession;
    protected Coordinate coordinate;
    protected Planet planet;
    protected double weight;
    protected Rocket rocket;
    public Person(){}
    public String getName (){
        return this.name;
    }
    @Override
    public void jump(Rocket rocket) {
        System.out.printf("%s is jumping out of the %s.\n", this.name, rocket.getModel());
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
        System.out.println(this.name + " stop. ");
    }

    @Override
    public void chase(Person person) {
        System.out.printf("%s is chasing after %s.\n", this.name, person.getName());
    }
}
