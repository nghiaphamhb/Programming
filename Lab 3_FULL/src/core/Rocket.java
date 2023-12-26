package core;
import java.util.Objects;
import utility.*;

public class Rocket extends Transport {
    private String model;
    private Coordinate coord;

    public Rocket(String model, Coordinate coord) {
        this.model = model;
        this.coord = coord;
    }

    @Override
    public void move(Coordinate coord) {
        this.coord = coord;
    }

    public String getModel() {
        return model;
    }

    public Coordinate getCoord() {
        return coord;
    }@Override
    public String toString() {
        return "Rocket{" +
                "model='" + model + '\'' +
                ", abs=" + this.coord.getAbs() +
                ", ord=" + this.coord.getOrd() +
                '}';
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(model);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rocket rocket = (Rocket) obj;
        return Objects.equals(model, rocket.model);
    }
}