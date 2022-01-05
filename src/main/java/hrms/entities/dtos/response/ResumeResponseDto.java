package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponseDto {

    private List<ContactResponseDto> contacts;
    private List<JobExperienceResponseDto> jobExperiences;
    private JobSeekerResponseDto jobSeeker;
    private List<LanguageResponseDto> languages;
    private List<SchoolResponseDto> schools;
    private List<SkillResponseDto> skills;

}
