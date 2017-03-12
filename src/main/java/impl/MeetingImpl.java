package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 */
public abstract class MeetingImpl implements Meeting {
    private int id = 1;
    private Calendar date;
    private Set<Contact> contacts;

    public MeetingImpl (int id, Calendar date, Set<Contact> contacts) {
        if ((id == 0) || (date == null) || (contacts == null)) { //how to check for contacts set not empty?
            throw new NullPointerException();
        }
        if (id < 1) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}
