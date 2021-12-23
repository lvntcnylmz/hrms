package hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerRegisterDto {

    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String nationalId;
    private String email;
    private String password;

}
