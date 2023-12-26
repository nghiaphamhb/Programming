package Pokemons;
import Moves.SpecialMoves.MudSlap;
import Moves.StatusMoves.Confide;
import Moves.StatusMoves.Minimize;
import Moves.StatusMoves.VenomDrench;
import ru.ifmo.se.pokemon.*;
public class Muk extends Grimer {
    public Muk (String name, int level){
        super(name, level);
        setStats(105, 105, 75,65,100,50);
        addMove(new VenomDrench());
    }
}
