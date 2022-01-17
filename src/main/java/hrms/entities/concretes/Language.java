package hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int id;

    @NotBlank
    @Column(name = "language_name")
    private String languageName;

    @NotBlank
    @Range(min = 1, max = 5)
    @Column(name = "level")
    private int level;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

}
