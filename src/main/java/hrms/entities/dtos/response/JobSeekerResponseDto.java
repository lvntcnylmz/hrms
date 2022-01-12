package hrms.entities.dtos.response;

//import hrms.entities.concretes.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerResponseDto {

    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;

}
