package datacategories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class Business implements Serializable {
    String businessId;
    String name;
    String address;
    String city;
    String state;
    String postalCode;
    Double latitude;
    Double longitude;
    Double stars;
    int reviewCount;
    int isOpen;
    Map<String, String> attributes;
    List<String> categories;
    Map<String, String> hours;

    public Business(BusinessJson businessJson) {
        this.businessId = businessJson.getBusinessId();
        this.address = businessJson.getAddress();
        this.name = businessJson.getName();
        this.city = businessJson.getCity();
        this.state = businessJson.getState();
        this.postalCode = businessJson.getPostalCode();
        this.latitude = businessJson.getLatitude();
        this.longitude = businessJson.getLongitude();
        this.stars = businessJson.getStars();
        this.reviewCount = businessJson.getReviewCount();
        this.isOpen = businessJson.getIsOpen();
        this.attributes = Optional.ofNullable(businessJson.getAttributes()).orElse(null);
        this.hours = Optional.ofNullable(businessJson.getHours()).orElse(null);
        if (businessJson.getCategories() != null) {
            this.categories = Arrays.asList(businessJson.getCategories().split(","));
        }
    }

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
}
