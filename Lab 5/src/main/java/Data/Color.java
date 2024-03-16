package Data;

public enum Color {
    GREEN ("зелёный"),
    YELLOW ("жёлтый"),
    WHITE ("белый");
    private String description;
    private Color (String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
