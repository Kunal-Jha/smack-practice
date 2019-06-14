package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

import static datacategories.Utils.getMapper;

@Data
public class CheckInJson implements Serializable {
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("date")
    String date;

    public static CheckIn parseJson(String json) throws IOException {
        ObjectMapper objectMapper = getMapper();
        return new CheckIn(objectMapper.readValue(json, CheckInJson.class));
    }


}
