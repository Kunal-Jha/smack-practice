package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

import static datacategories.Utils.getMapper;

@Data
public class CheckIn implements Serializable {
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("date")
    String date;

    public static CheckIn parseJson(String json) throws IOException {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.readValue(json, CheckIn.class);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "businessId='" + businessId + '\'' +
                ", date=" + date +
                '}';
    }
}
