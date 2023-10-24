package Moves.PhysicalMoves;
import ru.ifmo.se.pokemon.*;
public class RockTomb extends PhysicalMove{
//    Rock Tomb gây sát thương và giảm Tốc độ của mục tiêu đi một bậc.
//    Chỉ số có thể được hạ xuống tối thiểu -6 mỗi giai đoạn
    public RockTomb(){
        super(Type.ROCK, 60, 95);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {

        p.setMod(Stat.SPEED, -1);
    }
    protected String describe(){
        return "использует способность Rock Tomb";
    }
}
