import ru.ifmo.se.pokemon.*;
import Pokemons.*;
public class Battle {
    public static void main(String[] args){
        ru.ifmo.se.pokemon.Battle b = new ru.ifmo.se.pokemon.Battle();
        Pokemon p1 = new Grimer("A", 10);
        Pokemon p2 = new Hawlucha("B",10 );
        Pokemon p3 = new Muk("C", 10);
        Pokemon p4 = new Togekiss("D", 1);
        Pokemon p5 = new Togepi("E", 2);
        Pokemon p6 = new Togetic("B", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}
