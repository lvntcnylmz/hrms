package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobExperience;

public interface JobExperienceService {
    
    Result add(JobExperience  jobExperience);

    DataResult<List<JobExperience>> getAll();

}
