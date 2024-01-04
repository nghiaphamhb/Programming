package core;

import utility.Place;

public class Landforms {
    public Landforms() {}
    public Place createPlace (String name, int abs, int ord){
        Place place = new Place(name, new Coordinate(abs, ord)) {
            @Override
            public void printActionCreatePlace (){
                System.out.println(name + " was successfully created.");
            }
        };
        place.printActionCreatePlace();
        return place;
    }

}
