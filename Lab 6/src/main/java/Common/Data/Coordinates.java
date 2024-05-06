package Common.Data;

import java.io.Serializable;

/**
 * Dragon coordinates
 */
public class Coordinates implements Serializable {
    private Integer x; //The maximum value of the field is 830, the field cannot be null
    private Long y; //the field cannot be null

    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x +
                "," + y +
                ')';
    }
}