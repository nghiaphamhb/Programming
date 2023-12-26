public class Rocket extends Transport {
    public Rocket(String model, Coordinate coord) {
        this.model = model;
        this.coord = coord;
    }

    @Override
    public void Move(Coordinate coord) {
        this.coord = coord;
    }
    public String getModel(){
        return model;
    }
    public double getAbs() {
        return coord.getAbs();
    }

    public double getOrd() {
        return coord.getOrd();
    }
}