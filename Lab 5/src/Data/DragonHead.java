package Data;

/**
 * Голова у дракона
 */
public class DragonHead {
    private long eyesCount; //Поле может быть null

    public DragonHead(long eyesCount) {
        this.eyesCount = eyesCount;
    }

    /**
     *
     * @return у головы дракона сколько глаз
     */
    @Override
    public String toString() {
        return "имеет " + eyesCount +
                " глаз";
    }
}

