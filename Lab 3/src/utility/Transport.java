package utility;

import core.Coordinate;

import java.util.Objects;

public abstract class Transport {
    protected String model;
    protected Coordinate coord;
    public void move (Coordinate coord){};

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Transport transport = (Transport) obj;
        return model.equals(transport.model);
    }
    @Override
    public int hashCode(){return Objects.hash(model);
    };
    @Override
    public String toString (){

        return "Transport{"+
                "model=" + model + "\n" +
                "abs=" + coord.getAbs() +
                "ord=" + coord.getOrd() +
                '}';
    };
}
