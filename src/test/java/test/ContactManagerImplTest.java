package test;

import impl.ContactManagerImpl;
import impl.MeetingImpl;
import impl.PastMeetingImpl;
import org.junit.Test;
import spec.Contact;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Eileen
 */
public class ContactManagerImplTest {

    @Test
    public void test_addNewPastMeeting() {
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




}
