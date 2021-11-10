package hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank
    @Column(name = "school_name")
    private String schoolName;

    @NotBlank
    @Column(name = "departmant_name")
    private String departmentName;

    //FIXME : date format accurs SQL query error...
    @NotBlank
    @Column(name = "start_date")
    private LocalDate startDate;
    
    //FIXME : date format accurs SQL query error...
    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "is_graduated")
    private boolean isGraduated;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
