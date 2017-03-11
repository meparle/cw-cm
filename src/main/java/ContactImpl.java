/**
 * Created by eileen on 01/03/2017.
 */
public class ContactImpl implements Contact {
    private int id = 0;
    private String name = "";
    private String notes = "";

    public ContactImpl (int id, String name, String notes) {
        if ((id == 0) || (name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    public ContactImpl (int id, String name) {
        if ((id == 0) || (name == null)) {
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        if (notes == null) {
            return "";
        } else {
            return notes;
        }
    }

    public void addNotes(String note) {
        notes = notes + note;
    }
}
