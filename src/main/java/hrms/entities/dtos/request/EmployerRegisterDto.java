package hrms.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegisterDto {

    private String email;
    private String password;
    private String companyName;
    private String webSite;
    private String phoneNumber;

}
