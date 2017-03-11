package test; /**
 * Created by eileen on 01/03/2017.
 */
import impl.ContactImpl;
import org.junit.*;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.Assert.*;

public class ContactImplTest {

    @Test
    public void tests_name_with_null() {
        try {
            new ContactImpl(12345, null);
            fail("Expected exception.");
        } catch (NullPointerException expected) {
        }
    }

    @Test
    public void tests_id_negative() {
        try {
            new ContactImpl(-2, "Fred", "note sample text");
            fail ("Expected exception.");
        } catch (IllegalArgumentException expected) {
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
}
