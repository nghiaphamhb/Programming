package core;

public class RocketException extends RuntimeException {  //unchecked exception
    public RocketException(String message) {
        super(message);
    }
}