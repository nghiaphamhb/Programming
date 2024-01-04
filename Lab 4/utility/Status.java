package utility;

public enum Status {
    WEIGHTLESS ("Everybody lost weight."),
    WEIGHTED("Everybody gained weight.");
    private final String description;
    Status(String description){
        this.description = description;
    }
    public String getDescription(){
        return  this.description;
    }

}
