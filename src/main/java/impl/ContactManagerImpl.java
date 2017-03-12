package impl;

import spec.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by eileen on 01/03/2017.
 * @InheritDoc spec.ContactManager
 */

public class ContactManagerImpl implements ContactManager {
    private int maxContactId = 0;
    private int maxMeetingId = 0;

    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
//        date.set(2000, 1, 31, 13, 30, 0)
        return 1;
    }

    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }

    public Meeting getMeeting(int id) {
        return null;
    }

    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        return null;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        return null;
    }

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        if ((contacts == null) || (date == null) || (text == null)) {
            throw new NullPointerException();
        }
        Calendar cal = Calendar.getInstance();
        if (date.after(cal)) {
            throw new IllegalArgumentException();
        }
        int id = maxMeetingId + 1;
        maxMeetingId = id;
        MeetingImpl mi = new PastMeetingImpl(id, date, contacts, text);
        return id;
    }

    public PastMeeting addMeetingNotes(int id, String text) {
        return null;
    }

    public int addNewContact(String name, String notes) {
        if ((name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if ((name == "") || (notes == "")) {
            throw new IllegalArgumentException();
        }
        int id = maxContactId + 1;
        maxContactId = id;
        ContactImpl contact = new ContactImpl(id, name, notes);
        return id;
    }

    public Set<Contact> getContacts(String name) {
//        Set<Contact> contacts = new HashSet<>(); //need to read contacts from file.io
//        Set<Contact> retrievedContacts = new HashSet<>();
//        for (ContactImpl x in Set<Contact> contacts) {
//            if (x.getName() == name) {
//                retrievedContacts.add(x);
//            }
//        }
//        return retrievedContacts;
        return null;
    }

    public Set<Contact> getContacts(int... ids) {

        return null;
    }

    public void flush() {

    }
}
