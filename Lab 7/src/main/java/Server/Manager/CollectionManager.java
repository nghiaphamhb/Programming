package Server.Manager;

import Common.Data.Dragon.Dragon;
import Common.Data.User;
import Server.ServerApp;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * * Dragon Collection Management: Dragon collections + Collection initialization/save time
 */

public class CollectionManager {
    private HashSet<Dragon> collection;
    private ConcurrentHashMap<Long, Dragon> synchronizedCollection;
    private static LocalDateTime lastInitTime;
    private LocalDateTime saveTime;
//    private static final Set<Long> idList = new HashSet<>();   //to control the uniqueness of thís attribute
    private DatabaseCollectionManager databaseCollectionManager;

    public CollectionManager(DatabaseCollectionManager databaseCollectionManager) {
        this.databaseCollectionManager = databaseCollectionManager;
        loadCollection();
        convertCollectionStructure();
        saveTime = null;
    }
    private void loadCollection(){
        try{
            collection  = databaseCollectionManager.getCollection();
            lastInitTime = LocalDateTime.now();
            ServerApp.logger.log(Level.INFO, "Collection's data was loaded from database." );
        } catch (Exception e){
            collection = new HashSet<>();
            ServerApp.logger.log(Level.WARNING, e.toString());
        }
    }

    private void convertCollectionStructure(){
        synchronizedCollection = new ConcurrentHashMap<>();
        for (Dragon dragon : collection) {
            synchronizedCollection.put(dragon.getId(), dragon);
        };
    }

    /**
     * Check the presence of the dragon by ID
     * @param id the ID of the dragon that you want to know if there is in the collection
     * @return dragon presence
     */
//    public static boolean checkExistById (Long id){
//        for ( Long i : idList ){
//            if (Objects.equals(i, id)) return true;
//        }
//        return false;
//    }

    /**
     * Return the dragon by ID
     * @param id dragon ID
     * @return dragon
     */
    public Dragon getById( Long id ){
        return synchronizedCollection.get(id);
    }

    /**
     * Bring back the dragon collection
     * @return collection of dragons
     */
    public Set<Dragon> getCollection (){
        return new HashSet<Dragon>(synchronizedCollection.values());
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
        return synchronizedCollection.getClass().getName();
    }

    /**
     * Return the size of the collection
     * @return collection size
     */
    public int getCollectionSize () {
        return synchronizedCollection.size();
    }

    /**
     * Bring back the first dragon
     * @return the first dragon
     */
    public Dragon getFirstDragon () {
        Dragon firstDragon = null;
        if (!synchronizedCollection.isEmpty()) {
            Map.Entry<Long, Dragon> firstEntry = synchronizedCollection.entrySet().iterator().next();
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
        if (!synchronizedCollection.isEmpty()) {
            Map.Entry<Long, Dragon> lastEntry = null;
            for (Map.Entry<Long, Dragon> entry : synchronizedCollection.entrySet()) {
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
        synchronizedCollection.put(dragon.getId(), dragon);
//        idList.add( dragon.getId() );
    }

    /**
     * Remove the dragon from the collection
     * @param dragon remote dragon
     */
    public void removeFromCollection(Dragon dragon){
        synchronizedCollection.remove(dragon.getId());
//        idList.remove( dragon.getId() );
    }

    /**
     * Clean the collection (remove all dragons in the collection)
     * */
    public void clearCollection(){
        synchronizedCollection.clear();
//        idList.clear();
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
        if (synchronizedCollection.isEmpty()) return "The collection is empty.";
        // Chuyển đổi HashSet thành ArrayList
        List<Dragon> dragonList = new ArrayList<>(synchronizedCollection.values());
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
