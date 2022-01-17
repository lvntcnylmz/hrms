package hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "is_graduated")
    private boolean isGraduated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

}
