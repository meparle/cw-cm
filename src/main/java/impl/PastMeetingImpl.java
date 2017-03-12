package impl;

import spec.Contact;
import spec.PastMeeting;

import java.util.Set;
import java.util.Calendar;

/**
 * Created by eileen on 01/03/2017.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private String notes = "";

    public PastMeetingImpl (int id, Calendar date, Set<Contact> contacts, String notes) {
        super(id, date, contacts);
        if (notes == null) {
            throw new NullPointerException();
        }
        this.notes = notes;
    }

    public String getNotes() {
        if (notes == null) {
            return "";
        } else {
            return notes;
        }
    }
}
