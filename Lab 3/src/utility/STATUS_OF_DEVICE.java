package utility;

public enum STATUS_OF_DEVICE {
    ON("The device is on. The gravity disappeared."),
    OFF("The device is off. The gravity existed.");
    private final String description;
    STATUS_OF_DEVICE(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

}
