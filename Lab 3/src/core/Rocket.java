package core;

import utility.Transport;

import java.util.Objects;

public class Rocket extends Transport {
    private String model;
    private Coordinate coord;

    public Rocket (String model, Coordinate coord){
        this.model = model;
        this.coord = coord;
    }

    public void setModel (String model){
        this.model = model;
    }
    public void setCoord (double abs, double ord){
        this.coord.setAbs(abs);
        this.coord.setOrd(ord);
    }

    public String getModel(){
        return this.model;
    }
    public Coordinate getCoord (){
        return this.coord;
    }

    @Override
    public void move(Coordinate coord) {
        this.coord = coord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rocket rocket = (Rocket) obj;
        return Objects.equals(model, rocket.model);
    }
    @Override
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


}
