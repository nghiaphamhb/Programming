package utility;

import core.Coordinate;

public abstract class Transport {
    protected String model;
    protected Coordinate coord;
    public void Move (Coordinate coord){};

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public int hashCode(Object object){
        return object.hashCode();
    };
    public String toString (){
        return "";
    };
}
