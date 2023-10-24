package Moves.PhysicalMoves;
import ru.ifmo.se.pokemon.*;
public class SteelWing extends PhysicalMove {
//    Cánh Thép gây sát thương và có 10% cơ hội nâng Phòng thủ của người dùng lên một bậc.
//    Chỉ số có thể được nâng lên tối đa +6 mỗi cấp.
    public SteelWing(){
        super(Type.STEEL, 70, 90);
    }
    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        if(Math.random()<=0.1){
            pokemon.setMod(Stat.DEFENSE, 1);
        }
    }
    protected String describe(){
        return "использует способность Steel Wing";
    }
}
