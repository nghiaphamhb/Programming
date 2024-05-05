package Common.Network;

import Common.Data.Dragon;
import java.io.Serializable;

public class Request implements Serializable {
    private final String nameCommand;
    private final Object argument;
    private Object bonus = null;

    public Request(String nameCommand) {
        this.nameCommand = nameCommand;
        this.argument = null;
    }

    public Request(String nameCommand, Dragon dragon) {
        this.nameCommand = nameCommand;
        this.argument = dragon;
    }

    public Request(String nameCommand, String nameDragon) {
        this.nameCommand = nameCommand;
        this.argument = nameDragon;
    }

    public Request(String nameCommand, long idDragon) {
        this.nameCommand = nameCommand;
        this.argument = idDragon;
    }

    public Request(String nameCommand, long idDragon, Dragon updatedDragon) {
        this.nameCommand = nameCommand;
        this.argument = idDragon;
        this.bonus = updatedDragon;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public Object getArgumentCommand() {
        return argument;
    }

    public Object getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "Request \"" + nameCommand + "[" + (argument!=null? argument.toString(): " ") + "][" + (bonus!=null? argument.toString():" ") + "]\"";
    }
}
