package Common.Data;

import java.io.Serializable;

/**
 * Dragon head
 */
public class DragonHead implements Serializable {
    private long eyesCount; //Can be null

    public DragonHead(long eyesCount) {
        this.eyesCount = eyesCount;
    }

    /**
     * Print information of dragon head
     * @return number of eyes
     */
    @Override
    public String toString() {
        return "has " + eyesCount +
                " eye(s)";
    }
}

