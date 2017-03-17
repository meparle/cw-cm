package impl;

import spec.*;

import java.util.*;

/**
 * Created by eileen on 01/03/2017.
 */

public class ContactManagerImpl implements ContactManager {
    private int maxContactId = 0;
    private int maxMeetingId = 0;
    private Set<Contact> contacts = new HashSet<>();
    private Set<PastMeeting> pastMeetings = new TreeSet<>();
    private Set<FutureMeeting> futureMeetings = new TreeSet<>();

    public int convertFutureToPast(Meeting meeting) {
        if (meeting.getDate().before(Calendar.getInstance()) && futureMeetings.contains(meeting)) {
            return addNewPastMeeting(meeting.getContacts(), meeting.getDate(), "");
        }
        return 0;
    }

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
            if (futureMeetings.contains(x)) {
                throw new IllegalStateException();
            }
        }
        return result;
    }

    public FutureMeeting getFutureMeeting(int id) {
        FutureMeeting result = null;
        for (FutureMeeting x : futureMeetings) {
            if (x.getId() == id) {
                result = x;
            }
            if (pastMeetings.contains(x)) {
                throw new IllegalStateException();
            }
        }
        return result;
    }

    public Meeting getMeeting(int id) {
        Meeting result;
        result = getPastMeeting(id);
        if (result == null) {
            result = getFutureMeeting(id);
        }
        return result;
    }


    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        return null;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        if (contact == null) {
            throw new NullPointerException();
        }
//          try {
//            if (getContacts(contact.getId())
//                catch IllegalArgumentException())
//        }

        //NPE if contact is null
        //look up contact in set of contacts to check they exist (IAE if not)
        //go through past meeting list and look in each set of contacts to see if they were in that meeting
        //if so store the meeting in the list
        //return list (okay if empty)
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
        //if (result != null) {
        //    convertFutureToPast(result);
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

    public Set<Contact> getContacts(String name) { //need to add reading contacts from file.io
        Set<Contact> retrievedContacts = new HashSet<>();
        for (Contact x : contacts) {
            if (x.getName().contains(name)) {
                retrievedContacts.add(x);
            }
        }
        return retrievedContacts;
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

    public boolean compareContacts(Set<Contact> first, Set<Contact> second) {
        boolean nameresult = false;
        boolean notesresult = false;
        boolean result = false;
        Contact firstContact;
        Contact secondContact;
        for (Contact x : first) {
            int cid = x.getId();
            for (Contact y : second) {
                if (y.getId() == cid) {
                    secondContact = y;
                    firstContact = x;
                    if (x.getName().equals(y.getName())) {
                        nameresult = true;
                    }
                    if (x.getNotes().equals(y.getNotes())) {
                        notesresult = true;
                    }
                }
            }
        }
        if (nameresult == notesresult) {
            result = true;
        }
        return result;
        //pull out each contact in first, check whether another contact with that id value exists in second
        //if so, compare each field of the contact sharing that id between sets
        //if each field has an equal value to the other field, then result = true
//        if (for Contact x : expected) {actual.contains(x);}) {
//            result = true
//        if ((for x :actual) {expected.contains(x)}) {
//            pass = true;

    }
}
