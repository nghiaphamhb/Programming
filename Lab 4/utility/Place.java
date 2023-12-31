package utility;

import core.Coordinate;

import java.util.Objects;

public abstract class Place {
    protected String name;
    protected Coordinate coord;

    public Place(String name, Coordinate coord) {
        this.name = name;
        this.coord = coord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Place thingsPlace = (Place) obj;
        return name.equals(thingsPlace.name);
    }
    @Override
    public int hashCode(){
        return Objects.hash(name);
    };
    @Override
    public String toString (){

        return name + " (" + coord.getAbs() +
                ", " + coord.getOrd() +
                "). ";
    };
}
