package test;

import impl.ContactImpl;
import impl.ContactManagerImpl;
import impl.MeetingImpl;
import impl.PastMeetingImpl;
import org.junit.Test;
import spec.Contact;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * @author Eileen
 */
public class ContactManagerImplTest {

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

//    @Test
//    public void test_GetContactsByName() {
//        Set<Contact> contacts = new HashSet<>();
//        contacts.add(new ContactImpl(1,"Bob","A capital fellow"));
//        contacts.add(new ContactImpl(2,"Walter","A grumpy old man"));
//        contacts.add(new ContactImpl(3,"Marilyn","A successful lady"));
//        contacts.add(new ContactImpl(4,"Bob","This is the other Bob"));
//        ContactManagerImpl cmi = new ContactManagerImpl();
//        Set<Contact> output = cmi.getContacts("Bob");
//        Set<Contact> expected = contacts;
//        assertEquals(expected, output);
//    }


}
