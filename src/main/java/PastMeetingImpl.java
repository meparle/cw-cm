import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    public PastMeetingImpl (int id, String date, Set<Contact> contacts, String notes) {
        if ((id == null) || (date == null) || (contacts == null) || (notes == null)) { //how to check for contacts set not empty?
            throw new NullPointerException();
        }
    }

    String getNotes() {
        if (notes == null) {
            return "";
        } else {
            return notes;
        }
    }
}
