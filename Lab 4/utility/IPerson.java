package utility;

import core.Planet;
import core.Rocket;
import core.ZeroGravityDevice;

public interface IPerson {   // có thể tách ra thành nhiều interface sau
    public void atPlace (Place place);
    public void goOut(Rocket rocket);
    public void notice(String what);
    public void stretchOut (Planet planet);
    public void think(String what);
    public void shout();
    public void grab (ZeroGravityDevice device);
}
