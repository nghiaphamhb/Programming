package core;

import utility.Profession;

public class Astronaut extends Person{
    private final Profession profession = Profession.AUSTRONAUT;
    public Astronaut(String name, Planet planet, double weight) {
        super(name, planet, weight);
    }

    public Astronaut(String name, Coordinate coordinate, Planet planet, double weight) {
        super(name, coordinate, planet, weight);
    }

}
