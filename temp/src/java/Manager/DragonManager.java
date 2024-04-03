package Manager;

import Data.Dragon;
import Manager.JSONManager.JSONReader;
import Manager.JSONManager.JSONWriter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * * Управление коллекции драконов: Коллекции драконов + время инициализации/ сохранения коллекции
 */

public class DragonManager {
    private Set<Dragon> collection;
    private JSONWriter JSONWriter;
    private static LocalDateTime createTime;
    private LocalDateTime saveTime;
    private static final Set<Long> idList = new HashSet<>();   //to control the uniqueness of thís attribute

    public DragonManager(JSONReader JSONReader, JSONWriter JSONWriter) {
        collection  = JSONReader.read();
        createTime = LocalDateTime.now();
        saveTime = null;
        this.JSONWriter = JSONWriter;
    }

    /**
     * Проверить присутствие дракона по идентификатору
     * @param id идентификатор дракона, который хотите знать в коллекции есть ли
     * @return присутствие дракона
     */
    public static boolean checkExistById (long id){
        for ( Long i : idList ){
            if ( i == id ) return true;
        }
        return false;
    }

    /**
     * Вернуть дракона по идентификатору
     * @param id идентификатор дракона
     * @return дракон
     */
    public Dragon getById( long id ){
        for ( Dragon dragon : collection){
            if ( checkExistById(id) ) return dragon;
        }
        return null;
    }

    /**
     * Вернуть коллекцию драконов
     * @return коллекция драконов
     */
    public Set<Dragon> getCollection (){
        return collection;
    }

    /**
     * Вернуть время инициализации коллекции в данной сессии
     * @return время инициализации коллекции
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }


    /**
     * Вернуть время сохранения коллекции в данной сессии
     * @return время сохранения коллекции
     */
    public LocalDateTime getSaveTime() {
        return saveTime;
    }

    /**
     * Вернуть тип объектов в коллекции (дракон)
     * @return тип дракона
     */
    public String getCollectionType (){
        return collection.getClass().getName();
    }

    /**
     * Вернуть размер коллекции
     * @return размер коллекции
     */
    public int getCollectionSize () {
        return collection.size();
    }

    /**
     * Вернуть первого дракона
     * @return первый дракон
     */
    public Dragon getFirstDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon firstDragon = null;
        if ( iterator.hasNext() ) firstDragon = iterator.next();
        return firstDragon;
    }

    /**
     * Вернуть последнего дракона
     * @return последний дракон
     */
    public Dragon getLastDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon lastDragon = null;
        while( iterator.hasNext() ) lastDragon = iterator.next();
        return lastDragon;
    }

    /**
     * Добавить дракона в коллекции
     * @param dragon новый дракон
     */
    public void addToCollection( Dragon dragon ) {
        collection.add( dragon );
        idList.add( dragon.getId() );
        saveCollection();
    }

    /**
     * Удалить дракона из коллекции
     * @param dragon удаленный дракон
     */
    public void removeFromCollection(Dragon dragon){
        collection.remove( dragon );
        idList.remove( dragon.getId() );
        saveCollection();
    }

    /**
     * Чистить коллекцию ( удалить всех драконов в коллекции)
     */
    public void clearCollection(){
        collection.clear();
        idList.clear();
        saveCollection();
    }

    /**
     * Сохранить данную коллекцию в файл
     */
    public void saveCollection(){
        JSONWriter.write(collection);
        saveTime = LocalDateTime.now();
    }

    /**
     * Показать по очереди драконов в коллекции
     * @return информации каждого дракона
     */
    @Override
    public String toString() {
        if ( collection.isEmpty() ) return "Коллекция пуста!";
        StringBuilder info = new StringBuilder();
        for ( Dragon d : collection ) {
            info.append( d );
            if ( !d.equals( getLastDragon() ) ) info.append("\n");
        }
        return info.toString();
    }
}
