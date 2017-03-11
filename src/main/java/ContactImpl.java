/**
 * Created by eileen on 01/03/2017.
 */
public abstract class ContactImpl implements Contact {
    int id = "";
    String name = "";
    String notes = "";

    public ContactImpl (int id, String name, String notes) {
        if ((id == null) || (name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    public ContactImpl (int id, String name) {
        if ((id == null) || (name == null)) {
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getNotes() {
        if (notes == null) {
            return "";
        } else {
            return notes;
        }
    }

    void addNotes(String note) {
        notes = notes + note;
    }
}
