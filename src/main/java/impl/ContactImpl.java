package impl;

import spec.Contact;

/**
 * Implementation of {@link Contact}.
 *
 * @author Eileen Parle
 */
public class ContactImpl implements Contact {
    private final int id ;
    private final String name;
    private String notes = "";

    public ContactImpl(int id, String name, String notes) {
        if ((name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public ContactImpl(int id, String name) {
        this(id, name, "");
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
