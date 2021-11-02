package hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "Employers")
public class Employer extends User {
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotEmpty(message = "Company name cannot be empty.")
    @Column(name = "company_name")
    private String companyName;

    @NotEmpty(message = "Web site cannot be empty.")
    @Column(name = "web_site")
    private String webSite;

    @NotEmpty(message = "Phone number cannot be empty.")
    @Column(name = "phone_number")
    private String phoneNumber;
}
