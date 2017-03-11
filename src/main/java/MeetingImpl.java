import java.util.Calendar;
import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public abstract class MeetingImpl implements Meeting {

    public Meeting (int id, String date, Set<Contact> contacts) {
        if ((id == null) || (date == null) || (contacts == null)) { //how to check for contacts set not empty?
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
    }

    int getId() {
        return id;
    }

    Calendar getDate() {
        return date;
    }

    Set<Contact> getContacts() {
        return contacts;
    }
}
