package Data;

import java.util.Objects;

public class Coordinates {
    private Integer x; //Максимальное значение поля: 830, Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x +
                "," + y +
                ')';
    }
}