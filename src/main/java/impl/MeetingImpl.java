package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Implementation of {@link Meeting}.
 *
 * @author Eileen Parle
 */
public abstract class MeetingImpl implements Meeting, Comparable<MeetingImpl> {
    private final int id;
    private final Set<Contact> contacts;
    private Calendar date; // Non-final for test

    MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        if ((id == 0) || (date == null) || (contacts == null)) {
            throw new NullPointerException();
        }
        if (id < 1 || contacts.isEmpty()) {
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

    public void setDateForTest(Calendar setDate) {
        date = setDate;
    }

    @Override
    public int compareTo(MeetingImpl o) {
        if (date.equals(o.date)) {
            return id - o.id;
        }
        return date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingImpl meeting = (MeetingImpl) o;

        return id == meeting.id && date.equals(meeting.date) && contacts.equals(meeting.contacts);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date.hashCode();
        result = 31 * result + contacts.hashCode();
        return result;
    }
}
