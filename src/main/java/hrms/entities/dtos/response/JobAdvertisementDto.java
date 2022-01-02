package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

    private Integer id;
    private String employerName;
    private String jobTitle;
    private Boolean jobStatus;
    private Date applicationDeadline;
    private Integer numberOfOpenPosition;
    private String cityName;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;

}
