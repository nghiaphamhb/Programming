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
}
