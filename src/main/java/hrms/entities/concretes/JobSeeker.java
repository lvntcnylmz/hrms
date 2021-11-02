package hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "JobSeekers")
public class JobSeeker extends User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private User user;

    @NotEmpty(message = "First name cannot be empty.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "National ID cannot be empty.")
    @Size(min = 11, max = 11)
    @Column(name = "national_id", unique = true)
    private String nationalId;

    @NotEmpty(message = "Date of birth cannot be empty. e.g.: 2004")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

}
