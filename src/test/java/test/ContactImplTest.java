package test;

import impl.ContactImpl;
import org.junit.*;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.Assert.*;

/**
 * @author Eileen
 */
public class ContactImplTest {

    @Test
    public void tests_name_with_null() {
        try {
            new ContactImpl(12345, null);
            fail("Expected exception.");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void tests_id_negative() {
        try {
            new ContactImpl(-2, "Fred", "note sample text");
            fail ("Expected exception.");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void tests_getId() {
        int input = 2;
        ContactImpl c = new ContactImpl(input, "Ginger");
        int output = c.getId();
        int expected = 2;
        assertEquals(expected, output);
    }

    @Test
    public void tests_getNotes_empty() {
        ContactImpl c = new ContactImpl(2, "Ginger");
        String output = c.getNotes();
        String expected = "";
        assertEquals(expected, output);
    }

    @Test
    public void tests_getNotes() {
        ContactImpl c = new ContactImpl(2, "Ginger", "Productive Meeting");
        String output = c.getNotes();
        String expected = "Productive Meeting";
        assertEquals(expected, output);
    }

    @Test
    public void tests_getName() {
        ContactImpl c = new ContactImpl(2, "Ginger", "Productive Meeting");
        String output = c.getName();
        String expected = "Ginger";
        assertEquals(expected, output);
    }

    @Test
    public void tests_addNotes() {
        ContactImpl c = new ContactImpl(2, "Ginger", "Productive Meeting");
        c.addNotes("Definitely a buy");
        String output = c.getNotes();
        String expected = "Productive MeetingDefinitely a buy";
        assertEquals(expected, output);
    }

    @Test
    public void tests_addNotes_empty() {
        ContactImpl c = new ContactImpl(2, "Ginger");
        c.addNotes("Definitely a buy");
        String output = c.getNotes();
        String expected = "Definitely a buy";
        assertEquals(expected, output);
    }
}
