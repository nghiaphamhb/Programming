package core;

public class Coordinate {
    private double abs;
    private double ord;

    public Coordinate(double abs, double ord) {
        this.abs = abs;
        this.ord = ord;
    }

    public double getAbs() {
        return abs;
    }

    public double getOrd() {
        return ord;
    }

    public void setAbs(double abs) {
        this.abs = abs;
    }

    public void setOrd(double ord) {
        this.ord = ord;
    }
    public static double distance (Coordinate coord1, Coordinate coord2){
        double dt = Math.sqrt(Math.pow((coord1.getAbs()-coord2.getAbs()),2) +
                Math.pow((coord1.getOrd()-coord2.getOrd()),2));
        return dt;
    }
}