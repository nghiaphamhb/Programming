package Common.Data;

import java.io.Serializable;

/**
 * Координаты у дракона
 */
public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 830, Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Long getY() {
        return y;
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