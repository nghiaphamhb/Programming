package utility;

import core.*;

public interface IPerson {
    void moveToPlace (String namePlace, Coordinate coordinate);
    void getOut(Rocket rocket);
    void notice(String what);
    void stretchOut ();
    void think(String what);
    void shout();
    void chase (Person person);
    void grab (ZeroGravityDevice device);
    void move ();
    void runAndStop(Rocket rocket);
    void stop();
    void waited();
    void listen(Engineer engineer);
    void hugg(Engineer engineer);
    void jumpUpTo(Engineer engineer);
}
