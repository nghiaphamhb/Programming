package Moves.StatusMoves;
import ru.ifmo.se.pokemon.*;
public class BulkUp extends StatusMove{
//    Bulk Up tăng mỗi lần Tấn công và Phòng thủ của người dùng lên một bậc.
//    Chỉ số có thể được nâng lên tối đa +6 mỗi cấp.
    public BulkUp(){
        super(Type.FIGHTING, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.ATTACK, 1);
        p.setMod(Stat.DEFENSE, 1);
    }
    protected String describe(){
        return "использует способность Bulk Up";
    }
}
