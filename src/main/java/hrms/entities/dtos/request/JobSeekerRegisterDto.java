package hrms.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerRegisterDto {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;
    private String email;
    private String password;

}
