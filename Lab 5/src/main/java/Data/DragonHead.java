package Data;

import java.util.Objects;

public class DragonHead {
    private final long eyesCount; //Поле может быть null

    public DragonHead(long eyesCount) {
        this.eyesCount = eyesCount;
    }

    @Override
    public String toString() {
        return "имеет " + eyesCount +
                " глаз";
    }
}

