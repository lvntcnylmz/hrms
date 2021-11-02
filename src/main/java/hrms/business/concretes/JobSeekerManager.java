package hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.JobSeekerDao;
import hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;

    public JobSeekerManager(JobSeekerDao jobSeekerDao) {
        this.jobSeekerDao = jobSeekerDao;
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        this.jobSeekerDao.save(jobSeeker);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Candidates are listed.");
    }

}
