/**
 * Created by eileen on 01/03/2017.
 * @InheritDoc ContactManager
 */
import java.util.Calendar;
import java.util.Set;

public class ContactManagerImpl implements ContactManager{




    int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        if (contacts == null) || (date == null) || (text == null)) {
            throw new NullPointerException;
        }
        Calendar cal = Calendar.getInstance();
        if (date.after(cal)) {
            throw new IllegalArgumentException();
        }
        //look for an id that doesn't yet exist, and set it as the meeting id
        MeetingImpl mi = new PastMeetingImpl(id, date, contacts, text);
        return id;
    }

    PastMeeting addMeetingNotes(int id, String text) {

    }

    int addNewContact(String name, String notes) {
        if (name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if (name == "") || (notes == "")) {
            throw new IllegalArgumentException();
        }
        //look for an id that doesn't yet exist, and set it as the contact id
        ContactImpl contact = new ContactImpl(id, name, notes);
        return id;
    }

    Set<Contact> getContacts(String name) {
        //find contact in set, return it
    }

    Set<Contact> getContacts(int... ids) {

    }
}
