package utility;

import core.Coordinate;
import core.Person;
import core.Planet;

public interface IPersonRun {
    public void run (Coordinate coordinate, int steps);
    public void stop();
    public void chase (Person person);
    public void move (Place place);
}
