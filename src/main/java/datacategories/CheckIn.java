package datacategories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class CheckIn implements Serializable {


    String businessId;

    List<Date> date;

    public CheckIn(CheckInJson json) {
        this.businessId = json.getBusinessId();

        this.date = Arrays.asList(json.getDate().split(",")).stream()
                .map(a -> {
                    try {
                        return getDateFromString(a);
                    } catch (ParseException e) {
                        return null;
                    }
                }).collect(Collectors.toList());


    }

    public Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "businessId='" + businessId + '\'' +
                ", date=" + date +
                '}';
    }

}
