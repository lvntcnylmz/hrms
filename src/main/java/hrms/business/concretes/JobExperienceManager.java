package hrms.business.concretes;

import hrms.business.abstracts.JobExperienceService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobExperienceDao;
import hrms.entities.concretes.JobExperience;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;

    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    @Override
    public Result add(JobExperience jobExperience) {
        return new SuccessDataResult<JobExperience>(this.jobExperienceDao.save(jobExperience), Message.SAVED);
    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(), Message.LISTED);
    }

}
