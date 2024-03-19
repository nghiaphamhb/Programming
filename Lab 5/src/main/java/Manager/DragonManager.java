package Manager;

import Data.Dragon;
import utility.Asker;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This is a class for managing the collection of dragons: There are collection of dragons, time they were created and saved
 */
//t
public class DragonManager {
    private Set<Dragon> collection;
    private final LocalDateTime createTime;
    private LocalDateTime lastSaveTime;

    public DragonManager() {
        collection  = new HashSet<>();
        createTime = LocalDateTime.now();
        lastSaveTime = null;
    }

    public Set<Dragon> getCollection (){
        return collection;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String getCollectionType (){
        return collection.getClass().getName();
    }
    public int getCollectionSize () {
        return collection.size();
    }
    public Dragon getFirstDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon firstDragon = null;
        if ( iterator.hasNext() ) firstDragon = iterator.next();
        return firstDragon;
    }
    public Dragon getLastDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon lastDragon = null;
        while( iterator.hasNext() ) lastDragon = iterator.next();
        return lastDragon;
    }
    public Dragon getById (long id){
        for ( Dragon d : collection){
            if ( checkExistById(id) ) return d;
        }
        return null;
    }
    public boolean checkExistById (long id){
        for ( Dragon d : collection){
            if ( d.getId() == id ) return true;
        }
        return false;
    }
    public Dragon getByValue (Dragon dragonToFind){
        for ( Dragon d : collection ){
            if ( d.equals(dragonToFind) ) return  d;
        }
        return null;
    }
    public void addToCollection(Dragon element){
        collection.add(element);
        Dragon.toNextId();
    }
    public void removeFromCollection (Dragon element){
        collection.remove(element);
    }
    public void clearCollection(){
        collection.clear();
    }
    public void saveCollection(){
        lastSaveTime = LocalDateTime.now();
    }


    @Override
    public String toString() {
        if ( collection.isEmpty() ) return "Коллекция пуста!";
        StringBuilder info = new StringBuilder();
        for (Dragon d : collection){
            info.append(d);   //Java tự động gọi toString() của Dragon để chuyển đổi
            if ( !d.equals(getLastDragon())) info.append("\n");
        }
        return info.toString();
    }
}
