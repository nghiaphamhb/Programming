package utility;

import core.Coordinate;

import java.util.Objects;

public abstract class Place {
    protected String name;
    protected Coordinate coord;
    public Place(){}

    public Place(String name, Coordinate coord) {
        this.name = name;
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }
    abstract public void printLocationMarker ();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Place place = (Place) object;
        return Objects.equals(name, place.name) && Objects.equals(coord, place.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coord);
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", coord=" + coord +
                '}';
    }
}
