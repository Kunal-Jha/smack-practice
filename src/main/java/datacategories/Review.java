package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
public class Review implements Serializable {
    @JsonProperty("review_id")
    String reviewId;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("stars")
    Double stars;
    @JsonProperty("useful")
    int useful;
    @JsonProperty("funny")
    int funny;
    @JsonProperty("cool")
    int cool;
    @JsonProperty("text")
    String text;

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", userId='" + userId + '\'' +
                ", businessId='" + businessId + '\'' +
                ", stars=" + stars +
                ", useful=" + useful +
                ", funny=" + funny +
                ", cool=" + cool +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @JsonProperty("date")
    String date;

    public static Review parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Review.class);
    }

}
