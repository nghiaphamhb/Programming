package Moves.PhysicalMoves;
import ru.ifmo.se.pokemon.*;
public class ExtremeSpeed extends PhysicalMove{
    //Tốc độ cực cao gây sát thương và tấn công trước phần lớn các chiêu thức khác (ưu tiên +2).
    public ExtremeSpeed(){
        super(Type.NORMAL, 80, 100, 2, 1);
    }
    @Override
    protected String describe(){
        return "использует способность Extreme Speed";
    }

}
