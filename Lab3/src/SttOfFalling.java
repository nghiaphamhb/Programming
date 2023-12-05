public enum SttOfFalling {
    CRIPPLED("They will be crippled"),
    SAFE_WITH_SLIGHT_FRIGHT("They will escape with only a slight fright");

    private final String description;

    SttOfFalling(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}