package Pokemons;
import Moves.SpecialMoves.MagicalLeaf;
import Moves.SpecialMoves.Psychic;
import Moves.StatusMoves.ThunderWave;
import ru.ifmo.se.pokemon.*;
public class Togetic extends Togepi{
    public Togetic(String name, int level){
        super(name, level);
        setType(Type.FAIRY, Type.FLYING);
        setStats(55, 40, 85, 80, 105, 40);
        addMove(new MagicalLeaf());
    }
}
