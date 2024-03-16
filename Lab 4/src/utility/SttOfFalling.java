package utility;

public enum SttOfFalling {
    CRIPPLED("He will be crippled"),
    SAFE_WITH_SLIGHT_FRIGHT("He will escape with only a slight fright");
    private final String description;
    SttOfFalling(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
