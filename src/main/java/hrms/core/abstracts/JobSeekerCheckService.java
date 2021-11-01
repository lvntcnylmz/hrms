package hrms.core.abstracts;

import hrms.entities.concretes.JobSeeker;

public interface JobSeekerCheckService {
    
    boolean checkIfRealPerson(JobSeeker jobSeeker);

}
