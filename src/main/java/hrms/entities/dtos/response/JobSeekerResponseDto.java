package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerResponseDto {

    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;

}
