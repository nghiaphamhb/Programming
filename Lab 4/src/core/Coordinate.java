package core;

import java.util.Objects;

public class Coordinate {
    private double abs;
    private double ord;
    public Coordinate (double abs, double ord){
        this.abs = abs;
        this.ord = ord;
    }
    public double getAbs(){
        return this.abs;
    }
    public double getOrd(){
        return this.ord;
    }
    public void setAbs(double abs){
        this.abs = abs;
    }
    public void setOrd (double ord0){
        this.ord = ord;
    }
    public static double distance (Coordinate coor1, Coordinate coor2){
        return Math.sqrt(
                Math.pow( coor1.getAbs() - coor2.getAbs(), 2 ) +
                Math.pow( coor1.getOrd() - coor2.getOrd(), 2 )
        );
    }
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Coordinate coordinate = (Coordinate) otherObject;
        return Double.compare(coordinate.abs, abs) == 0  &&
                Double.compare(coordinate.ord, ord) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(abs)
                + 11 * Double.hashCode(ord);
    }
    public String toString (){
        return " (" + abs + ", " + ord + ")";
    }
}
