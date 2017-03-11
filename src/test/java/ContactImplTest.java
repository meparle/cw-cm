/**
 * Created by eileen on 01/03/2017.
 */
import org.junit.*;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.Assert.*;

public class ContactImplTest {

    @Test
    public tests_name_with_null() {
        String input = null;
        try {
            new ContactImpl(12345, input);
            fail("Expected exception.");
        } catch (NullPointerException expected) {

        }
    }

    @Test
    public tests_id_negative() {
        ContactImpl c = new ContactImpl();
        String input = "-2 Fred note_sample_text";
        String output =
        String expected
        assertEquals(expected, output);
    }

    @Test
    public tests_getId() {
        ContactImpl c = new ContactImpl();
        int input = 2;
        int output = c.getId();
        int expected = 2;
        assertEquals(expected, output);
    }
}
