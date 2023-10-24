package Moves.SpecialMoves;
import ru.ifmo.se.pokemon.*;
public class MudSlap extends SpecialMove{
//    Mud-Slap gây sát thương và giảm Độ chính xác của mục tiêu đi một bậc.
//    Chỉ số có thể được hạ xuống tối thiểu -6 mỗi cấp. Pokémon có khả năng Keen Eye,
//    Clear Body hoặc White Smoke không thể bị giảm độ chính xác.
    public MudSlap(){
        super(Type.GROUND, 20, 100);
    }
    protected void applyOppEffects (Pokemon p){

        p.setMod(Stat.ACCURACY, -1);
    }
    protected String describe(){
        return "использует способность Mud Slap";
    }
}
