package hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
@Table(name = "Employers")
public class Employer extends User {

    @NotNull(message = "Company name cannot be empty.")
    @Column(name = "company_name")
    private String companyName;

    @NotNull(message = "Web site cannot be empty.")
    @Column(name = "web_site")
    private String webSite;

    @NotNull(message = "Phone number cannot be empty.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<JobAdvertisement> jobAdvertisements;
}
