package Pokemons;
import Moves.PhysicalMoves.ExtremeSpeed;
import Moves.SpecialMoves.MagicalLeaf;
import Moves.SpecialMoves.Psychic;
import Moves.StatusMoves.ThunderWave;
import ru.ifmo.se.pokemon.*;
public class Togekiss extends Togetic{
    public Togekiss (String name, int level){
        super(name, level);
        setType(Type.FAIRY, Type.FLYING);
        setStats(85, 50, 95, 120, 115, 80);
        addMove(new ExtremeSpeed());
    }
}
