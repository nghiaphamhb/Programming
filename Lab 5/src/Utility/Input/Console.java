package Utility.Input;

import Data.Color;
import Data.Dragon;
import Utility.Display;
import Utility.Validator;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Получить информацию дракона от пользователя
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
    public void inputId(){
        Long id = null;
        do {
            Random random = new Random();
            long maxValue = 1000;
            long minValue = 0;
            id = minValue + (long) ( random.nextDouble() * ( maxValue - minValue ) );
        } while ( !validator.checkId(id) );
    }

    @Override
    public void inputName(){
        String name = null;
        Display.println( "Как зовут дракона?" );
        do {
            try {
                Display.ps2();
                name = scanner().nextLine();
            } catch ( InputMismatchException e ){
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkName(name) );
    }

    public void inputAbs(){
        Integer abs = null;
        Display.println("+ Абсцисса дракона:");
        do {
            try {
                Display.ps2();
                abs = scanner().nextInt();
            } catch ( InputMismatchException e ){
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkAbs(abs) );
    }


    public void inputOrd(){
        Long ord = null;
        Display.println("+ Ордината дракона:");
        do {
            try {
                Display.ps2();
                ord = scanner().nextLong();
            } catch ( InputMismatchException e ) {
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkOrd(ord) );
    }

    @Override
    public void inputCoordinate() {
        Display.println( "Введите координаты дракона:" );
        inputAbs();
        inputOrd();
    }

    public void inputAge() {
        Integer age = null;
        Display.println( "Введите возраст дракона (лет):" );
        do {
            try {
                Display.ps2();
                age = scanner().nextInt();
            } catch ( InputMismatchException e ) {
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkAge(age) );
    }

    public void inputWeight() {
        Long weight = null;
        Display.println("Введите вес дракона (кг):");
        do {
            try {
                Display.ps2();
                weight = scanner().nextLong();
            } catch ( InputMismatchException e ) {
                Display.printError( "Введен неверный тип данных." );
            } catch ( NumberFormatException  e ){
                Display.println( "Поле может быть null." );
            }
        } while ( !validator.checkWeight(weight) );
    }

    @Override
    public void inputSpeaking() {
        String speaking = null;
        Display.println( "Может ли дракон говорить? (true или false)" );
        do {
            try {
                Display.ps2();
                speaking = scanner().nextLine().toLowerCase();

                if (!speaking.equals("true") && !speaking.equals("false")) throw new IllegalArgumentException();
            } catch (NullPointerException e) {
                Display.println("");
            } catch (IllegalArgumentException e) {
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkSpeaking(speaking) ) ;
    }

    @Override
    public void inputColor(){
        Color color = null;
        Display.println("Какого цвета дракон?");
        Display.printTable("green", Color.GREEN);
        Display.printTable("yellow", Color.YELLOW);
        Display.printTable("white", Color.WHITE);
        do {
            try{
                Display.ps2();
                String strColor = scanner().nextLine().toUpperCase();
                color = Color.valueOf( strColor );
            } catch ( IllegalArgumentException e ){
                Display.printError("Недопустимый такой цвет.");
            }
        } while ( !validator.checkColor(color) );
    }

    @Override
    public void inputDragonHead() {
        Long eyes = null;
        Display.println("Сколько глаз у дракона?");
        do {
            try {
                Display.ps2();
                eyes = scanner().nextLong();
            } catch ( InputMismatchException e ) {
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkDragonHead(eyes) );
    }

    @Override
    public Dragon buildDragon() {
        inputId();
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
