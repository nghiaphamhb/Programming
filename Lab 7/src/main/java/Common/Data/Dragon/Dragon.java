package Common.Data.Dragon;

import Common.Data.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Dragon
 */

public class Dragon implements Serializable {
    private static int nextId = 1;
    private Long id; //The field cannot be null, the value of the field must be greater than 0,
    // The value of this field must be unique,
    // the value of this field must be generated automatically
    private String name; //The field cannot be null, the string cannot be empty
    private Coordinates coordinates; //The field cannot be null
    private LocalDateTime creationDate; //The field cannot be null,
    // The value of this field should be generated automatically
    private int age; //The field value must be greater than 0
    private Long weight; //The field value must be greater than 0, The field can be null
    private boolean speaking;
    private Color color; //The field can be null
    private DragonHead head;
    private User user;

    public Dragon() {
        this.id = 0L;
        this.name = "";
        this.coordinates = new Coordinates(0,0L);
        this.creationDate = LocalDateTime.now();
        this.age = 0;
        this.weight = null;
        this.speaking = false;
        this.color = null;
        this.head = new DragonHead();
        this.user = null;
    }

    public Dragon(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, int age, Long weight, boolean speaking, Color color, DragonHead head, User user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.speaking = speaking;
        this.color = color;
        this.head = head;
        this.user = user;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getWeight() {
        return weight;
    }

    public Boolean getSpeaking() {
        return speaking;
    }

    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setHead(DragonHead head) {
        this.head = head;
    }

    public DragonHead getHead() {
        return head;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Print all information of the dragon
     * @return dragon information
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
        info +=  "\n " + user;
        info += "\n------------------------------------------------------";
        return info;
    }
}
