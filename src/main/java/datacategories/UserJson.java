package datacategories;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import static datacategories.Utils.getMapper;

@Data
public class UserJson implements Serializable {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("review_count")
    private int reviewCount;
    @JsonProperty("yelping_since")
    private String yelpingSince;
    @JsonProperty("useful")
    private int useful;
    @JsonProperty("funny")
    private int funny;
    @JsonProperty("cool")
    private int cool;
    @JsonProperty("fans")
    private int fans;
    @JsonProperty("compliment_hot")
    private int complimentHot;
    @JsonProperty("compliment_more")
    private int complimentMore;
    @JsonProperty("compliment_profile")
    private int complimentProfile;
    @JsonProperty("compliment_cute")
    private int complimentCute;
    @JsonProperty("compliment_list")
    private int complimentList;
    @JsonProperty("compliment_note")
    private int complimentNote;
    @JsonProperty("compliment_plain")
    private int complimentPlain;
    @JsonProperty("compliment_cool")
    private int complimentCool;
    @JsonProperty("compliment_funny")
    private int complimentFunny;
    @JsonProperty("compliment_writer")
    private int complimentWriter;
    @JsonProperty("compliment_photos")
    private int complimentPhotos;
    @JsonProperty("elite")
    private String elite;
    @JsonProperty("friends")
    private String friends;
    @JsonProperty("average_stars")
    private Double averageStars;

    public static User parseJson(String json) throws IOException {
        ObjectMapper objectMapper = getMapper();
        return new User(objectMapper.readValue(json, UserJson.class));
    }

}
