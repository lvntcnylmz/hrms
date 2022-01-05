package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolResponseDto {

    private String schoolName;
    private String departmentName;
    private Boolean isGraduated;
    private LocalDateTime startDate;
    private LocalDateTime graduationDate;

}
