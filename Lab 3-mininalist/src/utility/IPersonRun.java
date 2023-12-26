package utility;

import core.Coordinate;
import core.Person;

public interface IPersonRun {
    public void run (Coordinate coordinate, int steps);
    public void stop();
    public void chase (Person person);
}
