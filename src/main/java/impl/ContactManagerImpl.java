package impl;

import spec.*;

import java.util.*;

/**
 * Created by eileen on 01/03/2017.
 * @InheritDoc spec.ContactManager
 */

public class ContactManagerImpl implements ContactManager {
    private int maxContactId = 0;
    private int maxMeetingId = 0;
    private Set<Contact> contacts = new HashSet<>();
    private Set<PastMeeting> pastMeetings = new TreeSet<>();
    private Set<FutureMeeting> futureMeetings = new TreeSet<>();

    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        if ((contacts == null) || (date == null)) {
            throw new NullPointerException();
        }
        Calendar cal = Calendar.getInstance();
        if (date.before(cal)) {
            throw new IllegalArgumentException();
        } for (Contact x : contacts) {
            getContacts(x.getId());
        }
        int id = maxMeetingId + 1;
        maxMeetingId = id;
        FutureMeetingImpl mi = new FutureMeetingImpl(id, date, contacts);
            futureMeetings.add(mi);
            return id;
    }

    public PastMeeting getPastMeeting(int id) {
        PastMeeting result = null;
        for (PastMeeting x : pastMeetings) {
            if (x.getId() == id) {
                result = x;
            }
            if (x.getDate().after(Calendar.getInstance())) {
                throw new IllegalStateException(); //this is clashing with the IAE in add past meeting method
            }
        }
        return result;
    }

    public FutureMeeting getFutureMeeting(int id) {
        //same as past meeting but without IllegalStateException
        return null;
    }

    public Meeting getMeeting(int id) {
        //implement getPastMeeting and getFutureMeeting, then check the date to choose which one
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
        }  for (Contact x : contacts) {
            getContacts(x.getId());
        }
        int id = maxMeetingId + 1;
        maxMeetingId = id;
        PastMeetingImpl mi = new PastMeetingImpl(id, date, contacts, text);
        pastMeetings.add(mi);
        return id;
    }

    public PastMeeting addMeetingNotes(int id, String text) {
        //check date vs now, if future get Future Meeting, if past getPastMeeting
        //use add Notes method
        //return as Past Meeting
        return null;
    }

    public int addNewContact(String name, String notes) {
        if ((name == null) || (notes == null)) {
            throw new NullPointerException();
        }
        if ((name.equals("")) || (notes.equals(""))) {
            throw new IllegalArgumentException();
        }
        int id = maxContactId + 1;
        maxContactId = id;
        ContactImpl contact = new ContactImpl(id, name, notes);
        contacts.add(contact);
        return id;
    }

    public Set<Contact> getContacts(String name) {
//        Set<Contact> contacts = new HashSet<>(); //need to add reading contacts from file.io
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
        Set<Contact> result = new HashSet<>();
        for (Contact x : contacts) {
            for (int id : ids) {
                if (x.getId() == id) { //try to match each contact we have to a contact ID in the array
                    result.add(x); //reimplement with sorted IDs and binary search and break if lots of IDs
                }
            }
        }
        if (result.isEmpty()) {
            throw new IllegalArgumentException("No ID provided or invalid ID(s) given.");
        }
        if (ids.length > result.size()) {
            throw new IllegalArgumentException("Invalid ID(s) given.");
        }
        return result;
    }

    public void flush() {

    }
}
