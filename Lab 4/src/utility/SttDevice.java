package utility;

public enum SttDevice {
    ON("The device was on."),
    OFF("The device was off.");

    private final String description;

    SttDevice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}