package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "JobSeekers")
public class JobSeeker extends User {

    @NotNull(message = "First name cannot be empty.")
    @Column
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    @Column
    private String lastName;

    @NotNull(message = "National ID cannot be empty.")
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String nationalId;

    @NotNull(message = "Date of birth cannot be empty. e.g.: 2004")
    @Column
    private String dateOfBirth;

    @OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
