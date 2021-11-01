package hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobSeekerService;
import hrms.dataAccess.JobSeekerDao;
import hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;

    public JobSeekerManager(JobSeekerDao jobSeekerDao) {
        this.jobSeekerDao = jobSeekerDao;
    }

    @Override
    public List<JobSeeker> getAll() {
        return this.jobSeekerDao.findAll();
    }

}
