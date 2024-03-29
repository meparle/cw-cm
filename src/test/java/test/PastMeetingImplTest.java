package test;

import impl.ContactImpl;
import impl.PastMeetingImpl;
import org.junit.Test;
import spec.Contact;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Eileen Parle
 */
public class PastMeetingImplTest {

    @Test
    public void test_pmi_getNotes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        String input = "Bob sounds interested.";
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new ContactImpl(1, "Bob", "Notes"));
        PastMeetingImpl testMI = new PastMeetingImpl(5, cal, contacts, input);
        String output = testMI.getNotes();
        String expected = "Bob sounds interested.";
        assertTrue(output.endsWith(expected));
    }

    @Test
    public void test_getId() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new ContactImpl(1, "Bob", "Notes"));
        int input = 5;
        PastMeetingImpl testMI = new PastMeetingImpl(input, cal, contacts, "Everything's coming up Milhouse");
        int output = testMI.getId();
        int expected = 5;
        assertEquals(expected, output);
    }

    @Test
    public void test_getDate() {
        Calendar past = Calendar.getInstance();
        past.add(Calendar.MONTH, -1);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new ContactImpl(1, "Bob", "Notes"));
        PastMeetingImpl testMI = new PastMeetingImpl(1, past, contacts, "Everything's coming up Milhouse");
        assertEquals(past, testMI.getDate());
    }

    @Test
    public void test_getContacts() {
        Calendar past = Calendar.getInstance();
        past.add(Calendar.MONTH, -1);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new ContactImpl(1,"Bob","A capital fellow"));
        PastMeetingImpl testMI = new PastMeetingImpl(1, past, contacts, "Everything's coming up Milhouse");
        assertEquals(contacts, testMI.getContacts());
    }
}
