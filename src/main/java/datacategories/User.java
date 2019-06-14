package datacategories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

    private String userId;
    private String name;

    private int reviewCount;
    private String yelpingSince;
    private int useful;
    private int funny;
    private int cool;
    private int fans;
    private int complimentHot;
    private int complimentMore;
    private int complimentProfile;
    private int complimentCute;
    private int complimentList;
    private int complimentNote;
    private int complimentPlain;
    private int complimentCool;
    private int complimentFunny;
    private int complimentWriter;
    private int complimentPhotos;
    private String elite;

    private Double averageStars;

    List<String> friends;

    public User(UserJson userJson) {
        this.userId = userJson.getUserId();
        this.name = userJson.getName();
        this.reviewCount = userJson.getReviewCount();
        this.yelpingSince = userJson.getYelpingSince();
        this.useful = userJson.getUseful();
        this.cool = userJson.getCool();
        this.fans = userJson.getFans();
        this.complimentHot = userJson.getComplimentHot();
        this.complimentMore = userJson.getComplimentMore();
        this.complimentProfile = userJson.getComplimentProfile();
        this.complimentCute = userJson.getComplimentCute();
        this.complimentList = userJson.getComplimentList();
        this.complimentNote = userJson.getComplimentNote();
        this.complimentPlain = userJson.getComplimentPlain();
        this.complimentCool = userJson.getComplimentCool();
        this.complimentFunny = userJson.getComplimentFunny();
        this.complimentWriter = userJson.getComplimentWriter();
        this.complimentPhotos = userJson.getComplimentPhotos();
        this.elite = userJson.getElite();
        this.averageStars = userJson.getAverageStars();
        this.friends = Arrays.asList(userJson.getFriends().split(","));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", reviewCount=" + reviewCount +
                ", yelpingSince='" + yelpingSince + '\'' +
                ", useful=" + useful +
                ", funny=" + funny +
                ", cool=" + cool +
                ", fans=" + fans +
                ", complimentHot=" + complimentHot +
                ", complimentMore=" + complimentMore +
                ", complimentProfile=" + complimentProfile +
                ", complimentCute=" + complimentCute +
                ", complimentList=" + complimentList +
                ", complimentNote=" + complimentNote +
                ", complimentPlain=" + complimentPlain +
                ", complimentCool=" + complimentCool +
                ", complimentFunny=" + complimentFunny +
                ", complimentWriter=" + complimentWriter +
                ", complimentPhotos=" + complimentPhotos +
                ", elite='" + elite + '\'' +
                ", friends='" + friends + '\'' +
                ", averageStars=" + averageStars +
                '}';
    }
}
