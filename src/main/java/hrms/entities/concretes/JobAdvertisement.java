package hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "OpenPositions")
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
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "application_deadline")
    private LocalDateTime applicationDeadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Employer employer;

    @Column(name = "status")
    private boolean isActive;

}
