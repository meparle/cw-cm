package impl;

import spec.Contact;
import spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Implementation of {@link FutureMeeting}.
 *
 * @author Eileen Parle
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }
}
