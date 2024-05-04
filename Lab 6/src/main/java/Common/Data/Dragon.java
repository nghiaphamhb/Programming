package Common.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Дракон
 */

public class Dragon implements Serializable {
    private static int nextId = 1;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private Long weight; //Значение поля должно быть больше 0, Поле может быть null
    private boolean speaking;
    private Color color; //Поле может быть null
    private DragonHead head;

    public Dragon() {
        this.id = 0L;
        this.name = "";
        this.coordinates = new Coordinates(0,0L);
        this.creationDate = LocalDateTime.now();
        this.age = 0;
        this.weight = null;
        this.speaking = false;
        this.color = null;
        this.head = null;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Dragon.nextId = nextId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public boolean getSpeaking() {
        return speaking;
    }

    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DragonHead getHead() {
        return head;
    }

    public void setHead(DragonHead head) {
        this.head = head;
    }

    /**
     * Вернуть информации у дракона в форме String
     * @return Информации у дракона
     */
    @Override
    public String toString() {
        String info = "\n";
        info += "Dragon with id: " + id;
        info += " (Added at: " + creationDate.toString() + ")";
        info += "\n Name: " + name;
        info += "\n Place: " + coordinates.toString();
        info += "\n Age: " + age;
        info += "\n Weight: " + ((weight != null) ? weight : null);
        info += "\n Can speak: " + ((speaking) ? "Yes" : "No");
        info += "\n Color: " + ( (color != null) ? color.toString() : "null");
        info += "\n Head: " + ( (head != null) ? head.toString() : "null");
        info += "\n------------------------------------------------------";
        return info;
    }
}
