package hrms.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegisterDto {

    @Email
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    private String password;

    @NotBlank(message = "Company name cannot be empty.")
    private String companyName;

    @NotBlank(message = "WebSite cannot be empty.")
    private String webSite;

    @NotBlank(message = "Phone number cannot be empty.")
    private String phoneNumber;

}
