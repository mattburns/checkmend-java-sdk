package uk.co.mattburns.checkmend;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import uk.co.mattburns.checkmend.differentpackage.Activity;

public class ActivityTest {

    @Test
    public void can_serialize_to_json() {
        Activity activity = new Activity(3, Activity.ActivityType.stolen, new Date(1));

        assertEquals(
                "{\"propertyid\":3,\"activity\":\"stolen\",\"datetime\":\"1970-01-01 01:00:00\"}",
                activity.toJson());
    }

}
