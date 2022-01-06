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

    @OneToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

    @OneToMany
    private List<School> schools;

    @OneToMany
    private List<JobExperience> jobExperiences;

    @OneToMany
    private List<Language> languages;

    @OneToMany
    private List<Skill> skills;

    @OneToMany
    private List<Photo> photos;

    @OneToMany
    private List<Contact> contacts;

}
