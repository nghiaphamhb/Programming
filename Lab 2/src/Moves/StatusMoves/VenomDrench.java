package Moves.StatusMoves;
import ru.ifmo.se.pokemon.*;
public class VenomDrench extends StatusMove{
    //Nếu mục tiêu bị nhiễm độc, Venom Drench sẽ giảm Tấn công, Tấn công Đặc biệt và Tốc độ mỗi lần một bậc.
    public VenomDrench(){
        super(Type.POISON, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Status status= pokemon.getCondition();
        if(status.equals(Status.POISON)){
            pokemon.setMod(Stat.ATTACK, -1);
            pokemon.setMod(Stat.SPECIAL_ATTACK, -1);
            pokemon.setMod(Stat.SPEED, -1);
        }
    }
    @Override
    protected String describe(){
        return "использует способность Venom Drench";
    }
}
