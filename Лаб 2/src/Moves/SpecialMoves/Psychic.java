package Moves.SpecialMoves;
import ru.ifmo.se.pokemon.*;
public class Psychic extends SpecialMove{
//    Tâm linh gây sát thương và có 10% cơ hội giảm Phòng thủ đặc biệt của mục tiêu xuống một bậc.
//    Chỉ số có thể được hạ xuống tối thiểu -6 mỗi cấp.
    public Psychic(){
        super(Type.PSYCHIC,90, 100);
    }
    protected void applyOffEffects (Pokemon p){
        if(Math.random()<=0.1){
            p.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }
    protected String describe(){
        return "использует способность Psychic";
    }
}
