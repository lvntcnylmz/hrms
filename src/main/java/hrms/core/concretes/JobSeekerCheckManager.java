package hrms.core.concretes;

import hrms.core.abstracts.JobSeekerCheckService;
import hrms.entities.concretes.JobSeeker;

public class JobSeekerCheckManager implements JobSeekerCheckService {

    private JobSeekerCheckService jobSeekerCheckService;

    public JobSeekerCheckManager(JobSeekerCheckService jobSeekerCheckService) {
        this.jobSeekerCheckService = jobSeekerCheckService;
    }

    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {
        return this.jobSeekerCheckService.checkIfRealPerson(jobSeeker);
    }

}
