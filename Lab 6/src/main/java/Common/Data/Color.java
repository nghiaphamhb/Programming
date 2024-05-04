package Common.Data;

import java.io.Serializable;

/**
 * Цвет у дракона
 */
public enum Color implements Serializable {
    GREEN ("GREEN"),
    YELLOW ("YELLOW"),
    WHITE ("WHITE");
    private final String description;

    private Color (String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
