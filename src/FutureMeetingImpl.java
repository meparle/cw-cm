import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl (int id, String date, Set<Contact> contacts) {
        if ((id == null) || (date == null) || (contacts == null)) { //how to check for contacts set not empty?
            throw new NullPointerException();
        }
    }
}
