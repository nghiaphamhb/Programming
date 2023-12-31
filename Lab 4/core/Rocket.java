package core;

import utility.Place;

public class Rocket extends Place {
    public Rocket (String name, Coordinate coordinate){
        super(name, coordinate);
    }

    public void move(Coordinate coord) throws RocketMoveException {
        if (coord == null) {
            throw new RocketMoveException(this.name + " could not move at all.");
        }
        else setCoord(coord);

    }
    public void descend (){
        System.out.println( getName() + " descended again.");
    }
    public void pushOff (int fuel) throws RocketPushOffException{
        if (fuel < 1000){
            throw new RocketPushOffException( getName() + " did not push off from the surface.");
        }
        else System.out.println( getName() + " pushed off successfully from the surface." ) ;
    }


}
