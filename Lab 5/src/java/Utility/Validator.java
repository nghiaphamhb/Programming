package Utility;

import Data.Color;
import Data.Dragon;
import Data.DragonHead;
import Manager.DragonManager;


/**
 * Проверка условия каждого атрибута у дракона
 */
public class Validator {
    private Dragon dragon;

    public Validator() {
        dragon = new Dragon();
    }

    /**
     * Проверить идентификатор у дракона
     * @param id идентификатор у дракона
     * @return результат проверки
     */
    public boolean checkId( Long id ) {
        if ( id <= 0 || DragonManager.checkExistById(id) || id == null ) return false;
        dragon.setId(id);
        return true;
    }

    /**
     * Проверить имя у дракона
     * @param name имя у дракона
     * @return результат проверки
     */
    public boolean checkName(String name) {
        if ( name.isEmpty() || name == null ) return false;
        dragon.setName(name);
        return true;
    }

    /**
     * Проверить абсцисс у дракона
     * @param abs абсцисс у дракона
     * @return результат проверки
     */
    public boolean checkAbs(Integer abs) {
        if (abs == null) return false;
        if (abs > 830) {
            Display.printError("Максимальное значение поля: 830");
            return false;
        }
        dragon.getCoordinates().setX(abs);
        return true;
    }

    /**
     * Проверить ординату у дракона
     * @param ord ординату у дракона
     * @return результат проверки
     */
    public boolean checkOrd(Long ord) {
        if (ord == null) return false;
        dragon.getCoordinates().setY(ord);
        return true;
    }

    /**
     * Проверить возраст у дракона
     * @param age возраст у дракона
     * @return результат проверки
     */
    public boolean checkAge(Integer age){
        if (age == null) return false;
        if (age <= 0){
            Display.printError("Возраст должен быть больше 0");
            return false;
        }
        dragon.setAge(age);
        return true;
    }

    /**
     * Проверить вес у дракона
     * @param weight вес у дракона
     * @return результат проверки
     */
    public boolean checkWeight(Long weight){
        if (weight == null)  return true;
        if (weight <= 0){
            Display.printError( "Вес должен быть больше 0." );
            weight = null;
            return true;
        }
        dragon.setWeight(weight);
        return true;
    }

    /**
     * Проверить способность говорить у дракона
     * @param speaking способность говорить у дракона
     * @return результат проверки
     */
    public boolean checkSpeaking(String speaking){
        if (!speaking.equals("true") && !speaking.equals("false")) return false;
        dragon.setSpeaking( Boolean.parseBoolean(speaking) );
        return true;
    }

    /**
     * Проверить цвет у дракона
     * @param color цвет у дракона
     * @return результат проверки
     */
    public boolean checkColor(Color color){
        if (color == null) return true;
        dragon.setColor(color);
        return true;
    }


    /**
     * Проверить голову у дракона
     * @param eyesCount количество глаз в голове у дракона
     * @return результат проверки
     */
    public boolean checkDragonHead(Long eyesCount){
        if (eyesCount == null) return true;
        if (eyesCount <= 0){
            Display.printError("Количество глаз должно быть больше 0.");
            return false;
        }
        dragon.setHead( new DragonHead(eyesCount) );
        return true;
    }

    /**
     * Вернуть проверенного дракона
     * @return новый дракон
     */
    public Dragon passedDragon (){
        return dragon;
    }
}
