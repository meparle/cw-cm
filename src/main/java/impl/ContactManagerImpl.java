package impl;

import spec.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.in;

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
            futureMeetings.remove(meeting);
            return addNewPastMeeting(meeting.getContacts(), meeting.getDate(), "");
        }
        return meeting.getId();
    }

    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        if ((contacts == null) || (date == null)) {
            throw new NullPointerException();
        }
        Calendar cal = Calendar.getInstance();
        if (date.before(cal)) {
            throw new IllegalArgumentException();
        }
        for (Contact x : contacts) {
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
        if (contact == null) {
            throw new NullPointerException();
        }
        Set<Meeting> contactMeetings = new HashSet<>();
        getContacts(contact.getId());
        for (FutureMeeting m : futureMeetings) {
            Set<Contact> meetingContacts = m.getContacts(); //for a past meeting, who was there
            if (meetingContacts.contains(contact)) { //if they were there, store that meeting
                contactMeetings.add(m);
            }
        }
        List<Meeting> list = new ArrayList<>(contactMeetings);
        return list;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        if (date == null) {
            throw new NullPointerException();
        }
        Set<Meeting> dateMeetings = new HashSet<>();
        Calendar cal = Calendar.getInstance();
        if (date.before(cal)) {
            for (PastMeeting m : pastMeetings) {
                if (compareCalendars(m.getDate(),date)) {
                    dateMeetings.add(m);
                }
                if (m.getDate().after(cal)) {
                    break;
                }//stop searching afterwards
            }
            List<Meeting> list = new ArrayList<>(dateMeetings);
            return list;
            //pull out each PastMeeting & check date for a match until reach desired date
            //once PastMeeting goes beyond desired date, stop searching
        } else {
            for (FutureMeeting m : futureMeetings) {
                if (compareCalendars(m.getDate(),date)) {
                    dateMeetings.add(m);
                }
                if (m.getDate().after(cal)) {
                    break;
                }//stop searching afterwards
            }
            List<Meeting> list = new ArrayList<>(dateMeetings);
            return list;
            //pull out each FutureMeeting & check date for a match until reach desired date
            //once FutureMeeting goes beyond desired date, stop searching
        }
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        if (contact == null) {
            throw new NullPointerException();
        }
        Set<PastMeeting> contactMeetings = new HashSet<>();
        getContacts(contact.getId()); //this will throw IAE if contact not in cmi's contact set
        for (PastMeeting m : pastMeetings) {
            Set<Contact> meetingContacts = m.getContacts(); //for a past meeting, who was there
            if (meetingContacts.contains(contact)) { //if they were there, store that meeting
                contactMeetings.add(m);
            }
        }
        List<PastMeeting> list = new ArrayList<>(contactMeetings);
        return list;
    }

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        if ((contacts == null) || (date == null) || (text == null)) {
            throw new NullPointerException();
        }
        Calendar cal = Calendar.getInstance();
        if (date.after(cal)) {
            throw new IllegalArgumentException();
        }
        for (Contact x : contacts) { //double check this
            getContacts(x.getId());
        }
        int id = maxMeetingId + 1;
        maxMeetingId = id;
        PastMeetingImpl mi = new PastMeetingImpl(id, date, contacts, text);
        pastMeetings.add(mi);
        return id;
    }

    public PastMeeting addMeetingNotes(int id, String text) {
        Meeting m = getMeeting(id);
        if (m == null) {
            throw new IllegalArgumentException();
        }
        if (text == null) {
            throw new NullPointerException();
        }
        Calendar cal = Calendar.getInstance();
        if (m.getDate().after(cal)) {
            throw new IllegalStateException();
        }
        int mid = convertFutureToPast(m);         //check date vs now, if future get Future Meeting, if past getPastMeeting
        //use add Notes method on Past Meeting
        //return as Past Meeting
        PastMeetingImpl meeting = (PastMeetingImpl) getPastMeeting(mid);
        meeting.addNotes(text);
        return meeting;
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

//    public void readContacts() {
//        ContactManagerImpl cmi = new ContactManagerImpl();
//        //look for file
//        try {             //to open file
//            File file = new File("/Users/eileen/Documents/src/cw-cm/src/main/ContactManagerContacts.csv");
//            BufferedReader in = new BufferedReader(new FileReader(file));
//            String line;
//            String splitBy = ",";
//            while ((line = in.readLine()) != null) {        //read from file into each field in Contact Manager Contacts
//                String[] contacts = line.split(splitBy);
//                //int id, String name, String notes
//                int cid = Integer.parseInt(contacts[0]);
//                String name = contacts[1];
//                String notes = contacts[2];
//                Calendar cal = Calendar.getInstance();
//                ContactImpl ci = new ContactImpl(cid, name, notes);
//                contacts.add(ci);
//            }
//        } catch (FileNotFoundException ex) {
//            File f = null;
//            f = new File("/Users/eileen/Documents/src/cw-cm/src/main", "ContactManagerContacts.csv");
//            f.createNewFile();
//            cmi.readMeetings();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//
//    public void readMeetings() {
//        ContactManagerImpl cmi = new ContactManagerImpl();
//        cmi.readContacts();
//        //look for file
//        try {             //to open file
//            File file = new File("/Users/eileen/Documents/src/cw-cm/src/main/ContactManagerMeetings.csv");
//            BufferedReader in = new BufferedReader(new FileReader(file));
//            String line;
//            String splitBy = ",";
//            while ((line = in.readLine()) != null) {        //read from file into each field in Contact Manager Meetings using reader
//                String[] meetings = line.split(splitBy);
//                //int id, Calendar date, Set<Contact> contacts, String notes
//                int mid = Integer.parseInt(meetings[0]);
//                String csvDate = meetings[1];
//                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//                Date date = format.parse(csvDate);
//                String csvContacts = meetings[2];
//                //this is a list of contact IDs as ints, need to look up in Contacts list and add to Set
//                String notes = meetings[3];
//                Calendar cal = Calendar.getInstance();
//                if (date.before(cal)) {
//                    PastMeetingImpl pmi = new PastMeetingImpl(mid, date, Set <Contact> csvContacts, notes);
//                    pastMeetings.add(pmi);
//                } else {
//                    FutureMeetingImpl fmi = new FutureMeetingImpl(mid, date, Set <Contact> csvContacts);
//                    futureMeetings.add(fmi);
//                }
//            }
//        }
//        catch (FileNotFoundException ex) {
//            File f = null;
//            System.out.println("File " + f + " does not exist."); //if file does not exist, create file
//            f = new File("/Users/eileen/Documents/src/cw-cm/src/main", "ContactManagerMeetings.csv");
//            f.createNewFile();
//            cmi.readMeetings();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

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

    public boolean compareCalendars(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
    }
}
