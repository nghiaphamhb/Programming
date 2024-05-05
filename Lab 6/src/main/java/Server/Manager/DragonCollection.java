package Server.Manager;

import Common.Data.Dragon;


import java.time.LocalDateTime;
import java.util.*;

/**
 * * Dragon Collection Management: Dragon collections + Collection initialization/save time
 */

public class DragonCollection {
    private Set<Dragon> collection;
    private FileManager fileManager;
    private static LocalDateTime createTime;
    private LocalDateTime saveTime;
    private static final Set<Long> idList = new HashSet<>();   //to control the uniqueness of thís attribute

    public DragonCollection(FileManager fileManager) {
        collection  = fileManager.readFromFile();
        createTime = LocalDateTime.now();
        saveTime = null;
        this.fileManager = fileManager;
    }

    /**
     * Check the presence of the dragon by ID
     * @param id the ID of the dragon that you want to know if there is in the collection
     * @return dragon presence
     */
    public static boolean checkExistById (Long id){
        for ( Long i : idList ){
            if (Objects.equals(i, id)) return true;
        }
        return false;
    }

    /**
     * Return the dragon by ID
     * @param id dragon ID
     * @return dragon
     */
    public Dragon getById( Long id ){
            for ( Dragon dragon : collection){
                if (Objects.equals(dragon.getId(), id)){
                    return dragon;
                }
            }
        return null;
    }

    /**
     * Bring back the dragon collection
     * @return collection of dragons
     */
    public Set<Dragon> getCollection (){
        return collection;
    }

    /**
     * Return the collection initialization time in this session
     * @return collection initialization time
     */
    public LocalDateTime getCreateTime() {
        return createTime;
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
        Iterator<Dragon> iterator = collection.iterator();
        Dragon firstDragon = null;
        if ( iterator.hasNext() ) firstDragon = iterator.next();
        return firstDragon;
    }

    /**
     * Bring back the last dragon
     * @return the last dragon
     */
    public Dragon getLastDragon () {
        Iterator<Dragon> iterator = collection.iterator();
        Dragon lastDragon = null;
        while( iterator.hasNext() ) lastDragon = iterator.next();
        return lastDragon;
    }

    /**
     * Add a dragon to the collection
     * @param dragon the new dragon
     */
    public void addToCollection(Dragon dragon) {
        collection.add(dragon);
        idList.add( dragon.getId() );
    }

    /**
     * Remove the dragon from the collection
     * @param dragon remote dragon
     */
    public void removeFromCollection(Dragon dragon){
        collection.remove( dragon );
        idList.remove( dragon.getId() );
    }

    /**
     * Clean the collection (remove all dragons in the collection)
     * */
    public void clearCollection(){
        collection.clear();
        idList.clear();
    }

    /**
     * Save this collection to a file
     * */
    public void saveCollection(){
        fileManager.writeIntoFile(collection);
        saveTime = LocalDateTime.now();
    }

    /**
     * Show the dragons in the collection in turn
     * @return of each dragon's information
     */
    @Override
    public String toString() {
        if ( collection.isEmpty() ) return "The collection is empty";

        // Chuyển đổi HashSet thành ArrayList
        List<Dragon> dragonList = new ArrayList<>(collection);

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
