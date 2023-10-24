package Pokemons;
import Moves.PhysicalMoves.RockTomb;
import Moves.PhysicalMoves.SteelWing;
import Moves.StatusMoves.BulkUp;
import Moves.StatusMoves.Confide;
import ru.ifmo.se.pokemon.*;
public class Hawlucha extends Pokemon{
    public Hawlucha(String name, int level){
        super(name, level );
        setType(Type.FIGHTING, Type.FLYING);
        setStats(78, 92, 75, 74, 63, 118);
        setMove(new SteelWing(), new RockTomb(), new BulkUp(), new Confide());
    }
}