package Server.Manager.Memory;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Server.Manager.Database.DatabaseCollectionManager;
import Server.ServerApp;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * * Dragon Collection Management: Dragon collections + Collection initialization/save time
 */

public class CollectionManager {
    private ConcurrentHashMap<Long, Dragon> collection;
    private static LocalDateTime lastInitTime;
    private LocalDateTime saveTime;
    private DatabaseCollectionManager databaseCollectionManager;

    public CollectionManager(DatabaseCollectionManager databaseCollectionManager) {
        this.databaseCollectionManager = databaseCollectionManager;
        loadCollection();
        saveTime = null;
    }

    /**
     * Load collection's data from DB
     */
    private void loadCollection(){
        try{
            collection  = databaseCollectionManager.getCollection();
            lastInitTime = LocalDateTime.now();
            ServerApp.logger.log(Level.INFO, "Collection's data was loaded from database." );
        } catch (Exception e){
            collection = new ConcurrentHashMap<>();
            ServerApp.logger.log(Level.WARNING, e.toString());
        }
    }

    /**
     * Return the dragon by ID
     * @param id dragon ID
     * @return dragon
     */
    public Dragon getById( Long id ){
        return collection.get(id);
    }

    /**
     * Bring back the dragon collection
     * @return collection of dragons
     */
    public Set<Dragon> getCollection (){
        return new HashSet<Dragon>(collection.values());
    }

    /**
     * Return the collection initialization time in this session
     * @return collection initialization time
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }


    /**
     * Return the time when the collection was saved in this session
     * @return collection save time
     */
    public LocalDateTime getSaveTime() {
        return saveTime;
    }

    /**
     * Return the type of objects in the collection (dragon)
     * @return the type of dragon
     */
    public String getCollectionType (){
        return collection.getClass().getName();
    }

    /**
     * Return the size of the collection
     * @return collection size
     */
    public int getCollectionSize () {
        return collection.size();
    }

    /**
     * Bring back the first dragon
     * @return the first dragon
     */
    public Dragon getFirstDragon () {
        Dragon firstDragon = null;
        if (!collection.isEmpty()) {
            Map.Entry<Long, Dragon> firstEntry = collection.entrySet().iterator().next();
            firstDragon = firstEntry.getValue();
        }
        return firstDragon;
    }

    /**
     * Bring back the last dragon
     * @return the last dragon
     */
    public Dragon getLastDragon () {
        Dragon lastDragon = null;
        if (!collection.isEmpty()) {
            Map.Entry<Long, Dragon> lastEntry = null;
            for (Map.Entry<Long, Dragon> entry : collection.entrySet()) {
                lastEntry = entry;
            }
            lastDragon = lastEntry.getValue();
        }
        return lastDragon;
    }

    /**
     * Add a dragon to the collection
     * @param dragon the new dragon
     */
    public void addToCollection(Dragon dragon, User user) {
        dragon.setUser(user);
        collection.put(dragon.getId(), dragon);
    }

    /**
     * Remove the dragon from the collection
     * @param dragon remote dragon
     */
    public void removeFromCollection(Dragon dragon){
        collection.remove(dragon.getId());
    }

    /**
     * Clean the collection (remove all dragons in the collection)
     * */
    public void clearCollection(){
        collection.clear();
        databaseCollectionManager.clearCollection();
    }

    /**
     * Save this collection into database
     * */
    public void saveCollection(){
        saveTime = LocalDateTime.now();

    }

    /**
     * Show the dragons in the collection in turn
     * @return of each dragon's information
     */
    @Override
    public String toString() {
        if (collection.isEmpty()) return "The collection is empty.";
        // Chuyển đổi HashSet thành ArrayList
        List<Dragon> dragonList = new ArrayList<>(collection.values());
        // Sắp xếp danh sách các con rồng theo tên
        dragonList.sort(Comparator.comparing(Dragon::getName));
        StringBuilder info = new StringBuilder();
        for ( Dragon d : dragonList ) {
            info.append( d );
            if ( !d.equals( getLastDragon() ) ) info.append("\n");
        }
        return info.toString();
    }
}
