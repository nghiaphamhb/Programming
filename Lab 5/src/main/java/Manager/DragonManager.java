package Manager;

import Data.Dragon;
import utility.Asker;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This is a class for managing the collection of dragons: There are collection of dragons, time they were created and saved, list of id's dragon
 */
//t
public class DragonManager {
    private Set<Dragon> collection;
    private DataManager dataManager;
    private LocalDateTime createTime;
    private LocalDateTime saveTime; //may be changed
    private static final Set<Long> idList = new HashSet<>();   //to control the uniqueness of thís attribute

    public DragonManager(DataManager dataManager) {
        collection  = new HashSet<>();
        createTime = LocalDateTime.now();
        saveTime = null;
        this.dataManager = dataManager;
    }

    /**
     * Check if this id is existed
     * @param id id, which is checked
     * @return it exist (yes/no)
     */
    public static boolean checkExistById (long id){
        for ( Long i : idList ){
            if ( i == id ) return true;
        }
        return false;
    }

    /**
     * Get dragon by its id
     * @param id id's dragon
     * @return dragon
     */
    public Dragon getById( long id ){
        for ( Dragon dragon : collection){
            if ( checkExistById(id) ) return dragon;
        }
        return null;
    }
    /**
     * Get the collection of dragons
     * @return collection of dragons
     */
    public Set<Dragon> getCollection (){
        return collection;
    }

    /**
     * Get the time when collection was created
     * @return time
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * Get the time when collection was saved
     * @return time
     */
    public LocalDateTime getSaveTime() {
        return saveTime;
    }

    /**
     * Get type of data in the collection
     * @return type of data
     */
    public String getCollectionType (){
        return collection.getClass().getName();
    }

    /**
     * Get size of the collection
     * @return size of collection
     */
    public int getCollectionSize () {
        return collection.size();
    }

    /**
     * Get the first dragon
     * @return first dragon
     */
    public Dragon getFirstDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon firstDragon = null;
        if ( iterator.hasNext() ) firstDragon = iterator.next();
        return firstDragon;
    }

    /**
     * Get the last dragon
     * @return last dragon
     */
    public Dragon getLastDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon lastDragon = null;
        while( iterator.hasNext() ) lastDragon = iterator.next();
        return lastDragon;
    }

    /**
     * Add dragon into collection
     * @param dragon added dragon
     */
    public void addToCollection( Dragon dragon ) {
        collection.add( dragon );
        idList.add( dragon.getId() );
        saveCollection();
    }

    /**
     * Remove dragon from collection
     * @param dragon removed dragon
     */
    public void removeFromCollection(Dragon dragon){
        collection.remove( dragon );
        idList.remove( dragon.getId() );
        saveCollection();
    }

    /**
     * Clear collection
     */
    public void clearCollection(){
        collection.clear();
        idList.clear();
        saveCollection();
    }

    /**
     * Save collection into file JSON (renew saved time)
     */
    public void saveCollection(){
        dataManager.writeCollection(collection);
        saveTime = LocalDateTime.now();
    }

    /**
     * Print in turn objects in the collection
     * @return string for printing
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
