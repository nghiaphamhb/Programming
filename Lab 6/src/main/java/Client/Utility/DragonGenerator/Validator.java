package Client.Utility.DragonGenerator;

import Common.Data.Color;
import Common.Data.Dragon;
import Common.Data.DragonHead;
import Client.Utility.Display;
import Server.Manager.DragonCollection;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Checking the condition of each attribute in the dragon
 */
public class Validator {
    private Dragon dragon;
    private final Logger logger;

    public Validator(Logger logger) {
        dragon = new Dragon();
        this.logger = logger;
    }

    /**
     * Check the dragon's ID
     * @param id the dragon's ID
     * @return verification result
     */
    public boolean checkId( Long id ) {
        if ( id <= 0 || DragonCollection.checkExistById(id) || id == null ) return false;
        dragon.setId(id);
        return true;
    }

    /**
     * Check the name of the dragon
     * @param name the name of the dragon
     * @return verification result
     */
    public boolean checkName(String name) {
        if ( name.isEmpty() || name == null ) return false;
        dragon.setName(name);
        return true;
    }

    /**
     * * Check the abscess in the dragon
     * @param abs abscess in a dragon
     * @return verification result
     */
    public boolean checkAbs(Integer abs) {
        if (abs == null) return false;
        if (abs > 830) {
            Display.printError("The maximum value of the field: 830");
            return false;
        }
        dragon.getCoordinates().setX(abs);
        return true;
    }

    /**
     * Check the ordinate in the Dragon
     * @param ord ordinate in the Dragon
     * @return verification result
     */
    public boolean checkOrd(Long ord) {
        if (ord == null) return false;
        dragon.getCoordinates().setY(ord);
        return true;
    }

    /**
     * Check the age of the dragon
     * @param age the age of the dragon
     * @return verification result
     */
    public boolean checkAge(Integer age){
        if (age == null) return false;
        if (age <= 0){
            Display.printError("The age must be more 0");
            return false;
        }
        dragon.setAge(age);
        return true;
    }

    /**
     * Check the weight of the dragon
     * @param weight dragon's weight
     * @return verification result
     */
    public boolean checkWeight(Long weight){
        if (weight == null)  return true;
        if (weight <= 0){
            Display.printError( "The weight must be greater than 0" );
            weight = null;
            return true;
        }
        dragon.setWeight(weight);
        return true;
    }

    /**
     * Test the dragon's ability to speak
     * @param speaking dragon's ability to speak
     * @return verification result
     */
    public boolean checkSpeaking(String speaking){
        if (!speaking.equals("true") && !speaking.equals("false")) return false;
        dragon.setSpeaking( Boolean.parseBoolean(speaking) );
        return true;
    }

    /**
     * Check the color of the dragon
     * @param color the color of the dragon
     * @return verification result
     */
    public boolean checkColor(Color color){
        if (color == null) return true;
        dragon.setColor(color);
        return true;
    }


    /**
     * Check the dragon's head
     * @param eyesCount Count the number of eyes in the dragon's head
     * @return verification result
     */
    public boolean checkDragonHead(Long eyesCount){
        if (eyesCount == null) return true;
        if (eyesCount <= 0){
            Display.printError("The number of eyes must be greater than 0");
            return false;
        }
        dragon.setHead( new DragonHead(eyesCount) );
        return true;
    }

    /**
     * Bring back a proven dragon
     * @return a new dragon
     */
    public Dragon passedDragon (){
        logger.log(Level.INFO, "Dragon was made successfully!");
        return dragon;
    }
}
