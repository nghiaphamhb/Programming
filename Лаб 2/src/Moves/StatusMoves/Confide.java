package Moves.StatusMoves;
import ru.ifmo.se.pokemon.*;
public class Confide extends StatusMove{
//    Tâm sự giảm sức tấn công đặc biệt của mục tiêu xuống một bậc.
//
//    Chỉ số có thể được hạ xuống tối thiểu -6 mỗi cấp.
    public Confide(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
            p.setMod(Stat.SPECIAL_ATTACK, -1);
    }
    @Override
    protected String describe(){
        return "использует способность Confide";
    }
}
