package utility;

public enum STATUS_OF_FALLING {
    CRIPPLED("He will be crippled"),
    SAFE_WITH_SLIGHT_FRIGHT("He will escape with only a slight fright");
    private final String description;
    STATUS_OF_FALLING (String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
