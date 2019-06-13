package datacategories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import static datacategories.Utils.getMapper;

@Data
public class Business implements Serializable {

    @JsonProperty("business_id")
    String businessId;
    @JsonProperty("name")
    String name;
    @JsonProperty("address")
    String address;
    @JsonProperty("city")
    String city;
    @JsonProperty("state")
    String state;
    @JsonProperty("postal_code")
    String postalCode;
    @JsonProperty("latitude")
    Double latitude;
    @JsonProperty("longitude")
    Double longitude;
    @JsonProperty("stars")
    Double stars;
    @JsonProperty("review_count")
    int reviewCount;
    @JsonProperty("is_open")
    int isOpen;
    @Nullable
    @JsonProperty("attributes")
    Map<String, String> attributes;
    @JsonProperty("categories")
    String categories;
    @Nullable
    @JsonProperty("hours")
    Map<String, String> hours;

    @Override
    public String toString() {
        return "Business{" +
                "businessId='" + businessId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", stars=" + stars +
                ", reviewCount=" + reviewCount +
                ", isOpen=" + isOpen +
                ", attributes=" + attributes +
                ", categories='" + categories + '\'' +
                ", hours=" + hours +
                '}';
    }


    public static Business parseJson(String json) throws IOException {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.readValue(json, Business.class);
    }
}
