package hrms.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {
    
    private int Id;
    private int jobId;
    private String companyName;
    private String jobTitle;
    private Boolean jobStatus;
    private LocalDateTime applicationDeadline;
    private Integer numberOfOpenPosition;   

}
