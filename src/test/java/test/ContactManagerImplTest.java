package test;

import impl.ContactImpl;
import impl.ContactManagerImpl;
import impl.FutureMeetingImpl;
import org.junit.Test;
import spec.*;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Eileen Parle
 */
public class ContactManagerImplTest {

    @Test
    public void test_addNewFutureMeetingUnknownContact() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        ContactManagerImpl cmi = new ContactManagerImpl();
        try {
            Set<Contact> contacts = new HashSet<>();
            cmi.addFutureMeeting(contacts, cal);
            cmi.getContacts(-1);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_addNewFutureMeetinginPast() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        ContactManagerImpl cmi = new ContactManagerImpl();
        try {
            Set<Contact> contacts = new HashSet<>();
            cmi.addFutureMeeting(contacts, cal);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_getPastMeeting() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        ContactManagerImpl cmi = new ContactManagerImpl();
        int cid = cmi.addNewContact("Bob", "Notes");
        Set<Contact> contacts = cmi.getContacts(cid);
        int id = cmi.addNewPastMeeting(contacts, cal, "Haha business!");
        String output = cmi.getPastMeeting(id).getNotes();
        String expected = "Haha business!";
        assertEquals(output, expected);
    }

    @Test
    public void test_getPastMeetingInFuture() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        ContactManagerImpl cmi = new ContactManagerImpl();
        try {
            Set<Contact> contacts = new HashSet<>();
            cmi.addNewPastMeeting(contacts, cal,"asdfjkl");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_getFutureMeeting() {
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.MONTH, 11);
        ContactManagerImpl cmi = new ContactManagerImpl();
        int cid = cmi.addNewContact("Humphrey", "A real gentleman");
        Set<Contact> contacts = cmi.getContacts(cid);
        int mid = cmi.addFutureMeeting(contacts, expected);
        Calendar output = cmi.getFutureMeeting(mid).getDate();
        expected.add(Calendar.MONTH, 11);
        assertEquals(output, expected);
    }

    @Test
    public void test_getMeetingPast() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Set<Contact> contacts;
        int cid = cmi.addNewContact("Humphrey", "A real gentleman");
        contacts = cmi.getContacts(cid);
        int mid = cmi.addNewPastMeeting(contacts, cal, "Good synergy");
        Set <Contact> output = cmi.getMeeting(mid).getContacts();
        Set <Contact> expected = contacts;
        assertEquals(output, expected);
    }

    @Test
    public void test_getMeetingFuture() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Set<Contact> contacts;
        int cid = cmi.addNewContact("Humphrey", "A real gentleman");
        contacts = cmi.getContacts(cid);
        int mid = cmi.addFutureMeeting(contacts, cal);
        Set <Contact> output = cmi.getMeeting(mid).getContacts();
        Set <Contact> expected = contacts;
        assertEquals(output, expected);
    }


    @Test
    public void test_getMeetingListOnNull() {
        try {
            ContactManagerImpl cmi = new ContactManagerImpl();
            cmi.getMeetingListOn(null);
            fail("Expected exception.");
        }
        catch (NullPointerException ignored) {
        }
    }

    @Test
    public void test_getMeetingListOn() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, -2);
        int cid = cmi.addNewContact("Walter","A grumpy old man");
        int cid2 = cmi.addNewContact("Orson","Rosebud");
        cmi.addNewPastMeeting(cmi.getContacts(cid), cal, "Grumpier");
        cmi.addNewPastMeeting(cmi.getContacts(cid2), cal, "Bobo");
        cmi.addNewPastMeeting(cmi.getContacts(cid,cid2), cal2, "What a twist!");
        List<Meeting> list = cmi.getMeetingListOn(cal);
        assertEquals(2, list.size());
        for (Meeting x : list) {
            assertTrue(cal.equals(x.getDate()));
            Set<Contact> actual = cmi.getContacts(cid, cid2);
            Set<Contact> expected = x.getContacts();
            assertTrue(compareContacts(actual,expected));
        }
    }

    @Test
    public void test_getFutureMeetingList() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, 2);
        Set<Contact> contacts;
        int cid = cmi.addNewContact("Walter","A grumpy old man");
        cmi.addFutureMeeting(cmi.getContacts(cid), cal);
        cmi.addFutureMeeting(cmi.getContacts(cid), cal2);
        contacts = cmi.getContacts(cid);
        Contact[] person = contacts.toArray(new Contact[1]);
        List <Meeting> list = cmi.getFutureMeetingList(person[0]);
        assertEquals(2, list.size());
        assertEquals(cal, list.get(0).getDate());
        assertEquals(cal2, list.get(1).getDate());
        for (Meeting x : list) {
            assertTrue(cal.equals(x.getDate()) || cal2.equals(x.getDate()));
        }
        assertTrue (list.get(0).getDate().before(list.get(1).getDate()));
    }

    @Test
    public void test_getPastMeetingListFor() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Set<Contact> contacts;
        int cid = cmi.addNewContact("Walter","A grumpy old man");
        cmi.addNewPastMeeting(cmi.getContacts(cid), cal,"Meeting 1");
        cmi.addNewPastMeeting(cmi.getContacts(cid), cal,"Meeting 2");
        contacts = cmi.getContacts(cid);
        Contact[] person = contacts.toArray(new Contact[1]);
        List<PastMeeting> list = cmi.getPastMeetingListFor(person[0]);
        assertEquals(2, list.size());
        for (PastMeeting x : list) {
            assertTrue("Meeting 1".equals(x.getNotes()) || "Meeting 2".equals(x.getNotes()));
        }
    }

    @Test
    public void test_getPastMeetingListForNull() {
        try {
            ContactManagerImpl cmi = new ContactManagerImpl();
            cmi.getPastMeetingListFor(null);
            fail("Expected exception.");
        }
        catch (NullPointerException ignored) {
        }
    }

    @Test
    public void test_addNewPastMeetingInFuture() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        ContactManagerImpl cmi = new ContactManagerImpl();
        try {
            Set<Contact> contacts = new HashSet<>();
            cmi.addNewPastMeeting(contacts, cal,"asdfjkl");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_addMeetingNotes() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.MONTH, -1);
        int cid = cmi.addNewContact("Norma", "I'm ready for my close-up");
        Set<Contact> contacts = cmi.getContacts(cid);
        int mid = cmi.addNewPastMeeting(contacts, date, "I am big");
        String notes = "It's the pictures that got small";
        PastMeeting pastMeeting = cmi.addMeetingNotes(mid, notes);
        assertEquals("I am big,It's the pictures that got small", pastMeeting.getNotes());
    }

    @Test
    public void test_addMeetingNotesNull() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.MONTH, -1);
        int cid = cmi.addNewContact("Norma", "I'm ready for my close-up");
        Set<Contact> contacts = cmi.getContacts(cid);
        int mid = cmi.addNewPastMeeting(contacts, date, "I am big");
        try {
            PastMeeting pastMeeting = cmi.addMeetingNotes(mid, null);
            fail();
        } catch (NullPointerException ignored){
        }
        try {
            PastMeeting pastMeeting = cmi.addMeetingNotes(1000,"It's the pictures that got small");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_addMeetingNotesFutureInPast() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int cid = cmi.addNewContact("Norma", "I'm ready for my close-up");
        Set<Contact> contacts = cmi.getContacts(cid);
        int mid = cmi.addFutureMeeting(contacts, date);
        FutureMeetingImpl theMeeting = (FutureMeetingImpl) cmi.getFutureMeeting(mid);
        Calendar pastDate = Calendar.getInstance();
        pastDate.add(Calendar.DATE,-1);
        theMeeting.setDateForTest(pastDate);
        //change date of meeting to past? access future meetings and add without using method that throws error?
        String notes = "I am big,It's the pictures that got small";
        PastMeeting pastMeeting = cmi.addMeetingNotes(mid, notes);
        assertEquals("I am big,It's the pictures that got small", pastMeeting.getNotes());
    }

    @Test
    public void test_addNewContactNulls() {
        try {
            ContactManagerImpl cmi =new ContactManagerImpl();
            cmi.addNewContact(null,null);
            fail("Expected exception.");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void test_addNewContactEmpties() {
        try {
            ContactManagerImpl cmi =new ContactManagerImpl();
            cmi.addNewContact("","");
            fail("Expected exception.");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void test_addNewContact() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        String name = "Charlie";
        String notes = "Enjoys slapstick.";
        int output = cmi.addNewContact(name, notes);
        name = "Humphrey";
        notes = "A real gentleman";
        int expected = cmi.addNewContact(name, notes);
        assertNotEquals(expected, output);
    }

    @Test
    public void test_getContactsById() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        int id = cmi.addNewContact("Bob","A capital fellow");
        cmi.addNewContact("Walter","A grumpy old man");
        cmi.addNewContact("Marilyn","A successful lady");
        cmi.addNewContact("Bob","This is the other Bob");
        Set<Contact> output = cmi.getContacts(id);
        assertEquals(1, output.size());
    }

    @Test
    public void test_getContactsByIds() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        int id1 = cmi.addNewContact("Bob","A capital fellow");
        int id2 = cmi.addNewContact("Walter","A grumpy old man");
        cmi.addNewContact("Marilyn","A successful lady");
        cmi.addNewContact("Bob","This is the other Bob");
        Set<Contact> output = cmi.getContacts(id1,id2);
        assertEquals(2, output.size());
    }

    @Test
    public void test_GetContactsByName() {
        ContactManagerImpl cmi = new ContactManagerImpl();
        int id1 = cmi.addNewContact("Bob","A capital fellow");
        int id2 = cmi.addNewContact("Bob","This is the other Bob");
        cmi.addNewContact("Walter","A grumpy old man");
        int id4 = cmi.addNewContact("Boberta","A successful lady");
        Set<Contact> actual = cmi.getContacts("Bob");
        Set<Contact> expected = new HashSet<>();
        expected.add(new ContactImpl(id1,"Bob","A capital fellow"));
        expected.add(new ContactImpl(id2,"Bob","This is the other Bob"));
        expected.add(new ContactImpl(id4,"Boberta","A successful lady"));
        assertTrue(compareContacts(actual,expected));
    }

    @Test
    public void test_flush() {
        String fileNameM = "/tmp/ContactManagerMeetings.csv";
        String fileNameC = "/tmp/ContactManagerContacts.csv";
        try {
            ContactManager cmi = ContactManagerImpl.fromFiles(fileNameM, fileNameC);
            int cid = cmi.addNewContact("Bob", "A capital fellow");
            Set<Contact> contacts = cmi.getContacts(cid);
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE, 1);
            int mid = cmi.addFutureMeeting(contacts, date);
            cmi.flush();
            ContactManager cmiAgain = ContactManagerImpl.fromFiles(fileNameM, fileNameC);
            Contact bobHope = cmiAgain.getContacts(cid).toArray(new Contact[1])[0];
            assertEquals("Bob", bobHope.getName());
            assertEquals("A capital fellow", bobHope.getNotes());
            FutureMeeting theShow = cmiAgain.getFutureMeeting(mid);
            Set<Contact> person = theShow.getContacts();
            Contact fellow = person.toArray(new Contact[1])[0];
            assertTrue(ContactManagerImpl.sameDate(date, theShow.getDate()));
            assertEquals("Bob", fellow.getName());
        } finally {
            new File(fileNameC).delete();
            new File(fileNameM).delete();
        }
    }

    private static boolean compareContacts(Set<Contact> first, Set<Contact> second) {
        boolean nameresult = false;
        boolean notesresult = false;
        boolean result = false;
        for (Contact x : first) {
            int cid = x.getId();
            for (Contact y : second) {
                if (y.getId() == cid) {
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

    }
}
