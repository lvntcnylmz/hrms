package hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "Employers")
public class Employer extends User {

    @NotEmpty(message = "Company name cannot be empty.")
    @Column
    private String companyName;

    @NotEmpty(message = "Web site cannot be empty.")
    @Column
    private String webSite;

    @NotEmpty(message = "Phone number cannot be empty.")
    @Column
    private String phoneNumber;
}
