package hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "JobAdvertisements")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Position name cannot be null.")
    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobPosition jobPosition;

    @NotNull(message = "Description cannot be null.")
    @Column(name = "description")
    private String description;

    @NotNull(message = "City cannot be null.")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

//    @DateTimeFormat(iso = ISO.DATE)
//    //@JsonFormat(pattern = "yyyy-MM-dd")
//    @Column(name = "creation_time")
//    private Date creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Employer employer;

    @Column(name = "number_of_open_position")
    private Integer numberOfOpenPosition;

    @Column(name = "status")
    private boolean status;

}
