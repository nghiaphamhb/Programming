package Utility.Input;

import Data.Dragon;

/**
 * Получить информацию дракона
 */
public interface Input {
    /**
     * Новый дракон получает свой идентификатор
     */
    void inputId();

    /**
     * Новый дракон получает свое имя
     */
    void inputName();

    /**
     * Новый дракон получает свои координаты
     */
    void inputCoordinate();

    /**
     * Новый дракон получает свой возраст
     */
    void inputAge();

    /**
     * Новый дракон получает свой вес
     */
    void inputWeight();

    /**
     * Новый дракон получает свою способности говорить
     */
    void inputSpeaking();

    /**
     * Новый дракон получает свой цвет
     */
    void inputColor();

    /**
     * Новый дракон получает свою голову
     */
    void inputDragonHead();

    /**
     * Через проверку всех условий каждого атрибута у нового дракона, создать его
     */
    Dragon buildDragon();

}
