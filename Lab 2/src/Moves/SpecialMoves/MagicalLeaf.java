package Moves.SpecialMoves;
import ru.ifmo.se.pokemon.*;
public class MagicalLeaf extends SpecialMove{
//    Chiếc lá ma thuật gây sát thương và bỏ qua các thay đổi về chỉ số Chính xác và Né tránh.
//    Không tấn công Pokémon trong giai đoạn bất khả xâm phạm của Bounce, Dig, Dive, Fly, Phantom Force, Shadow Force
//    hoặc Sky Drop.
    public MagicalLeaf(){
        super(Type.GRASS, 60, 100);
    }
    protected String describe(){
        return "использует способность Magical Leaf";
    }
}
