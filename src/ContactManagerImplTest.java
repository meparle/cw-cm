/**
 * Created by eileen on 04/03/2017.
 */
import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

public class ContactManagerImplTest {

    @Test
    public void test_addNewPastMeeting() {
        Calendar cal = Calendar.getInstance();
        try {
            addNewPastMeeting([Bob, Jane],cal + month, "asdfjkl")
            fail();
        } catch (NullPointerException expected) {
        }
    }

    @Test
    public void test_didMyNotesGetRecorded() {
        Calendar cal = Calendar.getInstance();
        String input = "Bob sounds interested.";
        MeetingImpl testMI = new PastMeetingImpl(5, cal - month, Bob, input);
        String output = testMI.getNotes();
        String expected = * + "Bob sounds interested.";
        assertEquals(expected, output);
    }


}
