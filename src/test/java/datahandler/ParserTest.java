package datahandler;

import datacategories.Photo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    @Test
    public void testParser() {
        final String json = "" +
                "{\"caption\": \"\", \"photo_id\": \"MllA1nNpcp1kDteVg6OGUw\"," +
                " \"business_id\": \"rcaPajgKOJC2vo_l3xa42A\", \"label\": \"inside\"}";
        final Photo result = new Photo("", "MllA1nNpcp1kDteVg6OGUw",
                "rcaPajgKOJC2vo_l3xa42A", "inside");
        try {
            final Photo output = Photo.parseJson(json);
            Assert.assertEquals(output, result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
