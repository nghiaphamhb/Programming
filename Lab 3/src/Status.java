public enum Status {
    WEIGHTED("Gravity exists"),WEIGHTLESS("Gravity dissappears");
    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}