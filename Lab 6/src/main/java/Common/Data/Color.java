package Common.Data;

import java.io.Serializable;

/**
 * Dragon color
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
