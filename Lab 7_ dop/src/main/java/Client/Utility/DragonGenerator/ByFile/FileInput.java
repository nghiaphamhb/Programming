package Client.Utility.DragonGenerator.ByFile;

import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon.Color;
import Common.Data.Dragon.Dragon;
import Client.Utility.Display;
import Client.Utility.DragonGenerator.Validator;

import java.util.InputMismatchException;

/**
 * Get dragon information through a script
 */
public class FileInput implements Input {
    private String[] lines;
    private Validator validator;

    public FileInput(String[] lines) {
        this.lines = lines;
        this.validator = new Validator();
    }

    @Override
    public void inputName(){
        String name = null;
        Display.println( "What is the dragon's name?" );
        do {
            try {
                Display.ps2();
                name = lines[0];
                Display.println(lines[0]);
            } catch ( InputMismatchException e ){
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkName(name) );
    }

    /**
     * Make abscissa
     */
    public void inputAbs(){
        Integer abs = null;
        Display.println("+ The Dragon's Abscissa:");
        do {
            try {
                Display.ps2();
                abs = Integer.valueOf(lines[1]);
                Display.println(lines[1]);
            } catch ( InputMismatchException e ){
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkAbs(abs) );
    }

    /**
     * Make ordinate
     */
    public void inputOrd(){
        Long ord = null;
        Display.println("+ The Dragon's Ordinate:");
        do {
            try {
                Display.ps2();
                ord = Long.valueOf(lines[2]);
                Display.println(lines[2]);
            } catch ( InputMismatchException e ) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkOrd(ord) );
    }

    @Override
    public void inputCoordinate() {
        Display.println( "Enter the coordinates of the dragon:" );
        inputAbs();
        inputOrd();
    }

    @Override
    public void inputAge() {
        Integer age = null;
        Display.println( "Enter the age of the dragon (years):" );
        do {
            try {
                Display.ps2();
                age = Integer.valueOf(lines[3]);
                Display.println(lines[3]);
            } catch ( InputMismatchException e ) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkAge(age));
    }

    @Override
    public void inputWeight() {
        Long weight = null;
        Display.println("Enter the dragon's weight (kg):");
        do {
            try {
                Display.ps2();
                weight = Long.valueOf(lines[4]);
                Display.println(lines[4]);
            } catch ( InputMismatchException e ) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkWeight(weight) );
    }

    @Override
    public void inputSpeaking() {
        String speaking = null;
        Display.println( "Can the dragon speak? (true or false)" );
        do {
            try {
                Display.ps2();
                speaking = lines[5].toLowerCase();
                Display.println(lines[5]);
                if (!speaking.equals("true") && !speaking.equals("false")) throw new IllegalArgumentException();
            } catch (NullPointerException e) {
                Display.println("");
            } catch (IllegalArgumentException e) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkSpeaking(speaking) ) ;
    }

    @Override
    public void inputColor(){
        Color color = null;
        Display.println("What color is the dragon?");
        Display.printTable("green", Color.GREEN);
        Display.printTable("yellow", Color.YELLOW);
        Display.printTable("white", Color.WHITE);
        do {
            try{
                Display.ps2();
                String strColor = lines[6].toUpperCase();
                Display.println(lines[6]);
                color = Color.valueOf( strColor );
            } catch ( IllegalArgumentException e ){
                Display.printError("This color is unacceptable");
            }
        } while ( !validator.checkColor(color) );
    }

    @Override
    public void inputDragonHead() {
        Long eyes = -1L;
        Display.println("How many eyes does a dragon have?");
        do {
            try {
                Display.ps2();
                eyes = Long.valueOf(lines[7]);
                Display.println(lines[7]);
            } catch ( InputMismatchException e ) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkDragonHead(eyes) );
    }

    @Override
    public Dragon buildDragon() {
        inputName();
        inputCoordinate();
        inputAge();
        inputWeight();
        inputSpeaking();
        inputColor();
        inputDragonHead();
        return validator.passedDragon();
    }
}
