package utility;

public enum SttDevice {
    ON("Device is on"),
    OFF("Device is off");

    private final String description;

    SttDevice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}