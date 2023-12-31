package core;

import utility.PROFESSION;

public class Astronaut extends Person{
    private final PROFESSION profession = PROFESSION.AUSTRONAUT;
    public Astronaut(String name, Planet planet, double weight) {
        super(name, planet, weight);
    }

    public Astronaut(String name, Coordinate coordinate, Planet planet, double weight) {
        super(name, coordinate, planet, weight);
    }
}
