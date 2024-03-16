package Manager;

import Data.Dragon;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//this is a class for managing the collection
public class DragonManager {
    private Set<Dragon> collection = new HashSet<>();
    private LocalDateTime createTime;
    private LocalDateTime lastSaveTime;

    public DragonManager() {
        createTime = null;
        lastSaveTime = null;
        loadCollection();
    }

//    kiểm tra tính hợp lệ của tất cả các tổ chức trong hệ thống; những điều này chưa chắc đã
//    và hiển thị thông báo lỗi thông qua đối tượng console nếu có tổ chức nào không hợp lệ
//    public void validateAll(Console console) {
//        Dragon.allDragons().values().forEach(dragon -> {
//            if (!dragon.validate()) {
//                console.printError("Дракон с id=" + dragon.getId() + " имеет невалидные поля.");
//            }
//        });
//        console.println("! Загруженные драконы валидны.");
//    }
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
    public Dragon getById (int id){
        for ( Dragon d : collection){
            if ( d.getId() == id ) return d;
        }
        return null;
    }
    public boolean checkExistById (int id){
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
    private void loadCollection() {
        createTime = LocalDateTime.now();
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
