package core;

import utility.Transport;

public class Rocket extends Transport {
    public Rocket (String model, Coordinate coord){
        this.model = model;
        this.coord = coord;
    }
    public String getModel(){
        return this.model;
    }
    public double getAbs (){
        return this.coord.getAbs();
    }
    public double getOrd (){
        return this.coord.getOrd();
    }
    public Coordinate getCoord (){
        return this.coord;
    }
}
