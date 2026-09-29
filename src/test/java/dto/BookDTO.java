package dto;

import lombok.Data;
import java.util.Map;

@Data
public class BookDTO {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private Map<String, String> bookingdates;
    private String additionalneeds;
}
