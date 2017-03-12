package impl;

import spec.Contact;
import spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl (int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }
}
