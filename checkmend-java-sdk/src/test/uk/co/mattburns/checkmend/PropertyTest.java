package uk.co.mattburns.checkmend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.mattburns.checkmend.Property.Category;

public class PropertyTest {

    @Test
    public void can_serialize_to_json() {
        Property property = new Property.PropertyBuilder(2, Category.Camera,
                "Canon", "123").withModel("7D").withDescription("My camera")
                .build();

        assertEquals(
                "{\"personid\":2,\"category\":\"5\",\"make\":\"Canon\",\"model\":\"7D\",\"description\":\"My camera\","
                        + "\"serials\":\"123\"}", property.toJson());
    }

    @Test
    public void multiple_serials_are_put_in_array() {
        Property property = new Property.PropertyBuilder(2, Category.Camera,
                "Canon", "123", "456").withModel("7D")
                .withDescription("My camera").build();

        assertEquals(
                "{\"personid\":2,\"category\":\"5\",\"make\":\"Canon\",\"model\":\"7D\",\"description\":\"My camera\","
                        + "\"serials\":[\"123\",\"456\"]}", property.toJson());
    }

}
