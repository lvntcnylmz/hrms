package hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int id;

    @Column(name = "skill_name")
    private String skillName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

}
