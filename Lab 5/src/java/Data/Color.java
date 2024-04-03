package Data;

/**
 * Цвет у дракона
 */
public enum Color {
    GREEN ("зелёный"),
    YELLOW ("жёлтый"),
    WHITE ("белый");
    private final String description;

    private Color (String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
