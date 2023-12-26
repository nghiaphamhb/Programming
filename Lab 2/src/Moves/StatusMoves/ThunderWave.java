package Moves.StatusMoves;
import ru.ifmo.se.pokemon.*;
public class ThunderWave extends StatusMove{
    public ThunderWave(){
        super(Type.ELECTRIC, 0, 90);
    }
//    Thunder Wave làm tê liệt đối thủ. Pokémon bị tê liệt có 25% khả năng không thể tấn công và Tốc độ của chúng bị
//        giảm 50% .
//    Pokémon hệ điện, những Pokémon có khả năng Limber hoặc những Pokémon đứng sau Người thay thế không thể bị tê liệt.

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.paralyze(pokemon);
    }
    protected String describe(){
        return "использует способность Thunder Wave";
    }
}
