package Client.Utility.DragonGenerator;

import Common.Data.Dragon;

/**
 * Get Dragon information
 */
public interface Input {
    /**
     * The new dragon gets its ID
     */
    void inputId();

    /**
     * A new dragon gets its name
     */
    void inputName();

    /**
     * The new dragon gets its coordinates
     */
    void inputCoordinate();

    /**
     * The new dragon gets its age
     */
    void inputAge();

    /**
     * The new dragon gets its weight
     */
    void inputWeight();

    /**
     * The new dragon gets his ability to speak
     */
    void inputSpeaking();

    /**
     * The new dragon gets its color
     */
    void inputColor();

    /**
     * A new dragon gets its head
     */
    void inputDragonHead();

    /**
     * After checking all the conditions of each attribute of the new dragon, create it
     */
    Dragon buildDragon();

}
