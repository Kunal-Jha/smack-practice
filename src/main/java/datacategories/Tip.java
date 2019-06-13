package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
public class Tip implements Serializable {
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("text")
    String text;
    @JsonProperty("date")
    String date;
    @JsonProperty("compliment_count")
    int complimentCount;

    public static Tip parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Tip.class);
    }

    @Override
    public String toString() {
        return "Tip{" +
                "userId='" + userId + '\'' +
                ", businessId='" + businessId + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", complimentCount=" + complimentCount +
                '}';
    }
}
