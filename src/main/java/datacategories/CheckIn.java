package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
public class CheckIn implements Serializable {
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("date")
    String date;

    public static CheckIn parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
