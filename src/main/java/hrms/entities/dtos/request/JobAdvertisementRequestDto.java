package hrms.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementRequestDto {

    private String employerName;
    private String jobTitle;
    private Boolean jobStatus;
    private LocalDate applicationDeadline;
    private Integer numberOfOpenPosition;
    private String cityName;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;

}
