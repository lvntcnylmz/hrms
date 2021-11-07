package hrms.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerWithOpenPositionDto {
    
    private String companyName;
    private String jobName;
    private int numberOfOpenPosition;
    private Date applicationDeadline;
    
}
