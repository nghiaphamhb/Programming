package utility;

public enum STATUS_OF_DEVICE {
    ON(" was on. "),
    OFF(" was off. ");
    private final String description;
    STATUS_OF_DEVICE(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

}
