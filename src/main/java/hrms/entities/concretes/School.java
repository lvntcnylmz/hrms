package hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Temporal;

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

    @Column(name = "departmant_name")
    private String departmentName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "is_graduated")
    private boolean isGraduated;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
