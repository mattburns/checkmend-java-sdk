package uk.co.mattburns.checkmend;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import uk.co.mattburns.checkmend.differentpackage.Person;

public class PersonTest {

    @Test
    public void can_serialize_to_json() {
        Person person = new Person.PersonBuilder("123").withFamilyname("smith")
                .withGender(Person.Gender.Male).withOthernames("bob")
                .withDob(new Date(1l)).build();

        assertEquals(
                "{\"familyname\":\"smith\",\"othernames\":\"bob\",\"dob\":\"1970-01-01\",\"gender\":\"Male\",\"ref\":\"123\"}",
                person.toJson());

        person = new Person.PersonBuilder("123").build();

        assertEquals("{\"ref\":\"123\"}", person.toJson());
    }

}
