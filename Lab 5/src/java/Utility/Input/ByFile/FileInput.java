package Utility.Input.ByFile;

import Data.Color;
import Data.Dragon;
import Utility.Display;
import Utility.Input.Input;
import Utility.Validator;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * Получить информацию дракона через скрипт
 */
public class FileInput implements Input {
    private String[] lines;
    private Validator validator;

    public FileInput(String[] lines) {
        this.lines = lines;
        this.validator = new Validator();
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
                name = lines[0];
                Display.println(lines[0]);
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
                abs = Integer.valueOf(lines[1]);
                Display.println(lines[1]);
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
                ord = Long.valueOf(lines[2]);
                Display.println(lines[2]);
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
                age = Integer.valueOf(lines[3]);
                Display.println(lines[3]);
            } catch ( InputMismatchException e ) {
                Display.printError("Введен неверный тип данных.");
            }
        } while ( !validator.checkAge(age));
    }

    public void inputWeight() {
        Long weight = null;
        Display.println("Введите вес дракона (кг):");
        do {
            try {
                Display.ps2();
                weight = Long.valueOf(lines[4]);
                Display.println(lines[4]);
            } catch ( InputMismatchException e ) {
                Display.printError( "Введен неверный тип данных." );
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
                speaking = lines[5].toLowerCase();
                Display.println(lines[5]);
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
        Display.println("Какого цвета дракон? (green/yellow/white)");
        do {
            try{
                Display.ps2();
                String strColor = lines[6].toUpperCase();
                Display.println(lines[6]);
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
                eyes = Long.valueOf(lines[7]);
                Display.println(lines[7]);
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
