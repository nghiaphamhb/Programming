package Common.Data.Dragon;

import java.io.Serializable;

/**
 * Dragon head
 */
public class DragonHead implements Serializable {
    private Long eyesCount; //Can be null; -1L ~~ null

    public DragonHead() {
        this.eyesCount = -1L;
    }

    public DragonHead(long eyesCount) {
        this.eyesCount = eyesCount;
    }

    public long getEyesCount() {
        return eyesCount;
    }

    public void setEyesCount(Long eyesCount) {
        this.eyesCount = eyesCount;
    }

    /**
     * Print information of dragon head
     * @return number of eyes
     */
    @Override
    public String toString() {
        return (eyesCount != -1L) ? getEyesCount() + " eyes" : "null";
    }
}

