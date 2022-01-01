package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResponseDto {

    private String userId;
    private String email;
    private String companyName;
    private String webSite;
    private String phoneNumber;

}
