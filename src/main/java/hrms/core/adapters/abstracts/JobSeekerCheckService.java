package hrms.core.adapters.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobSeeker;

public interface JobSeekerCheckService {
    
    Result checkIfRealPerson(JobSeeker jobSeeker) throws Exception;

}
