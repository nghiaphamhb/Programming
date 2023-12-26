package Moves.StatusMoves;
import ru.ifmo.se.pokemon.*;
public class Minimize extends StatusMove{
    public Minimize(){
        super(Type.NORMAL, 0, 0);
    }
//    Minimize tăng khả năng né tránh của người dùng lên hai giai đoạn, do đó khiến người dùng khó bị tấn công hơn.
//    Chỉ số có thể được nâng lên tối đa +6 mỗi cấp.
//    Các chiêu thức sau gây sát thương gấp đôi đối với Pokémon đã sử dụng Minimize và bỏ qua việc kiểm tra độ chính xác:
    @Override
    protected void applySelfEffects (Pokemon p) {
            p.setMod(Stat.EVASION, 2);
//        p.setMod(Stat.ATTACK, (int)p.getStat(Stat.ATTACK));
    }
    protected String describe(){
        return "использует способность Minimize";
    }
}
