import java.util.Calendar;
import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public abstract class MeetingImpl implements Meeting {
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public void Meeting (int id, Calendar date, Set<Contact> contacts) {
        if ((id == 0) || (date == null) || (contacts == null)) { //how to check for contacts set not empty?
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {

        return id;
    }

    public Calendar getDate() {
        date.set(2000, 1, 31, 13, 30, 0);
        return date;
    }

    public Set<Contact> getContacts() {

        return contacts;
    }
}
