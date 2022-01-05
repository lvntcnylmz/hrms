package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceResponseDto {

    private String jobTitle;
    private String companyName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
