package test;

import impl.PastMeetingImpl;
import org.junit.Test;
import spec.Contact;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by eileen on 12/03/2017.
 */
public class PastMeetingImplTest {

    @Test
    public void test_pmi_getNotes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        String input = "Bob sounds interested.";
        Set<Contact> contacts = new HashSet<>();
        PastMeetingImpl testMI = new PastMeetingImpl(5, cal, contacts, input);
        String output = testMI.getNotes();
        String expected = "Bob sounds interested.";
        assertTrue(output.endsWith(expected));
    }
}
