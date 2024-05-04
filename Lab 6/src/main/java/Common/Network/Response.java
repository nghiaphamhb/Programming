package Common.Network;


import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private final String message;
    private boolean isAccepted;
    private int newId;
    private List<Object> filteredList;


    public Response(String message) {
        this.message = message;
        this.isAccepted = false;
        this.newId = 0;
        this.filteredList = null;
    }

    public Response(String message, boolean isAccepted) {
        this.message = message;
        this.isAccepted = isAccepted;
        this.newId = 0;
        this.filteredList = null;
    }

    public Response(String message, int newId) {
        this.message = message;
        this.isAccepted = false;
        this.newId = newId;
        this.filteredList = null;
    }

    public Response(String message, List<Object> list) {
        this.message = message;
        this.isAccepted = false;
        this.newId = 0;
        this.filteredList = list;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public int getNewId() {
        return newId;
    }

    public List<Object> getFilteredList() {
        return filteredList;
    }
}
