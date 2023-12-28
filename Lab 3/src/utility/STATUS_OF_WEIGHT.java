package utility;

public enum STATUS_OF_WEIGHT {
    WEIGHTLESS ("Everybody lost weight."),
    WEIGHTED("Everybody gained weight.");
    private final String description;
    STATUS_OF_WEIGHT(String description){
        this.description = description;
    }
    public String getDescription(){
        return  this.description;
    }

}
