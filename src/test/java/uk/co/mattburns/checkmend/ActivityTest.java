package uk.co.mattburns.checkmend;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;
import uk.co.mattburns.checkmend.Activity;

public class ActivityTest {

    @Test
    public void can_serialize_to_json() {
        // Overwrite the locale timezone for the sake of testing.
        // Frankly this highlights the flaw that timezones are not supported.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Activity activity = new Activity(3, Activity.ActivityType.stolen, new Date(1));

        assertEquals(
                "{\"propertyid\":3,\"activity\":\"stolen\",\"datetime\":\"1970-01-01 12:00:00\"}",
                activity.toJson());
    }

}
