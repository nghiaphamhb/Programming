package Pokemons;
import Moves.SpecialMoves.MudSlap;
import Moves.StatusMoves.Confide;
import Moves.StatusMoves.Minimize;
import ru.ifmo.se.pokemon.*;
public class Grimer extends Pokemon{
    public Grimer(String name, int level){
        super(name, level);
        setType(Type.POISON);
        setStats(80, 80, 50,40,50,25);
        setMove(new Confide(), new MudSlap(), new Minimize());
    }
}
