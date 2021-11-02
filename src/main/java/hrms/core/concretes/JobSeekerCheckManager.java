package hrms.core.concretes;

import hrms.core.adapters.abstracts.JobSeekerCheckService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobSeeker;

public class JobSeekerCheckManager implements JobSeekerCheckService {

    private JobSeekerCheckService jobSeekerCheckService;

    public JobSeekerCheckManager(JobSeekerCheckService jobSeekerCheckService) {
        this.jobSeekerCheckService = jobSeekerCheckService;
    }

    @Override
    public Result checkIfRealPerson(JobSeeker jobSeeker) throws Exception {
        return this.jobSeekerCheckService.checkIfRealPerson(jobSeeker);
    }

}
