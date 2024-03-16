package Data;

import java.util.Objects;
import java.time.LocalDateTime;

public class Dragon {
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

    public Dragon(Long id, String name, Coordinates coordinates, int age, Long weight, boolean speaking, Color color, DragonHead head) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.age = age;
        this.weight = weight;
        this.speaking = speaking;
        this.color = color;
        this.head = head;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return LocalDateTime.now();
    }

    public int getAge() {
        return age;
    }

    public Long getWeight() {
        return weight;
    }

    public boolean getSpeaking() {
        return speaking;
    }

    public Color getColor() {
        return color;
    }

    public DragonHead getHead() {
        return head;
    }
    public static void toNextId (){
        nextId ++;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Дракон № " + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n Название: " + name;
        info += "\n Местоположение: " + coordinates.toString();
        info += "\n Возраст: " + age;
        info += "\n Вес: " + weight;
        info += "\n Умееть говорить: " + ((speaking) ? "Да" : "Нет");
        info += "\n Цвет: " + color.toString();
        info += "\n Голова: " + head.toString();
        info += "\n------------------------------------------------------";
        return info;
    }
}
