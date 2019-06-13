package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo implements Serializable {

    @JsonProperty("caption")
    String caption;
    @JsonProperty("photo_id")
    String photoId;
    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("label")
    String label;

    @Override
    public String toString() {
        return "Photo{" +
                "caption='" + caption + '\'' +
                ", photoId='" + photoId + '\'' +
                ", businessId='" + businessId + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public static Photo parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Photo.class);
    }
}
