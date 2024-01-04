package core;

public class RocketMoveException extends RuntimeException{     //unchecked exception
    public RocketMoveException(String message){
        super(message);
    }
}
