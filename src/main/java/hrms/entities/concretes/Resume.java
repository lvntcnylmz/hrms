package hrms.entities.concretes;

import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resumes")
public class Resume {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private int id;

    @Length(max = 300)
    @Column(name = "cover_letter")
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

    @OneToMany(mappedBy = "resume")
    private List<School> schools;

    @OneToMany(mappedBy = "resume")
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "resume")
    private List<Language> languages;

    @OneToMany(mappedBy = "resume")
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume")
    private List<Photo> photos;

    @OneToMany(mappedBy = "resume")
    private List<Contact> contacts;

}
