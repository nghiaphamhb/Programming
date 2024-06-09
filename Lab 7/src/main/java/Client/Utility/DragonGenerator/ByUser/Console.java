package Client.Utility.DragonGenerator.ByUser;

import Client.Utility.DragonGenerator.Input;
import Common.Data.Dragon.Color;
import Common.Data.Dragon.Dragon;
import Client.Utility.Display;
import Client.Utility.DragonGenerator.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Get dragon information from the user
 */
public class Console implements Input {
    private Validator validator;

    public Console() {
        this.validator = new Validator();
    }

    public Scanner scanner (){
        return new Scanner(System.in);
    }


    @Override
    public void inputName(){
        String name = null;
        Display.println( "What is the dragon's name?" );
        do {
            try {
                Display.ps2();
                name = scanner().nextLine();
            } catch ( InputMismatchException e ){
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkName(name) );
    }

    public void inputAbs(){
        Integer abs = null;
        Display.println("+ The Dragon's Abscissa:");
        do {
            try {
                Display.ps2();
                abs = scanner().nextInt();
            } catch ( InputMismatchException e ){
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkAbs(abs) );
    }


    public void inputOrd(){
        Long ord = null;
        Display.println("+ The Dragon's Ordinate:");
        do {
            try {
                Display.ps2();
                ord = scanner().nextLong();
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

    public void inputAge() {
        Integer age = null;
        Display.println( "Enter the age of the dragon (years):" );
        do {
            try {
                Display.ps2();
                age = scanner().nextInt();
            } catch ( InputMismatchException e ) {
                Display.printError("An invalid data type has been entered");
            }
        } while ( !validator.checkAge(age) );
    }

    public void inputWeight() {
        Long weight = null;
        Display.println("Enter the dragon's weight (kg):");
        do {
            try {
                Display.ps2();
                weight = scanner().nextLong();
            } catch ( InputMismatchException e ) {
                Display.printError( "An invalid data type has been entered" );
            } catch ( NumberFormatException  e ){
                Display.println( "The field can be null" );
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
                speaking = scanner().nextLine().toLowerCase();

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
        Display.printTable("\"green\"", Color.GREEN);
        Display.printTable("\"yellow\"", Color.YELLOW);
        Display.printTable("\"white\"", Color.WHITE);
        do {
            try{
                Display.ps2();
                String strColor = scanner().nextLine().toUpperCase();
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
                eyes = scanner().nextLong();
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
