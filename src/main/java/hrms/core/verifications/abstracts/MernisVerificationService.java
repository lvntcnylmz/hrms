package hrms.core.verifications.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobSeeker;

public interface MernisVerificationService {
    
    public Result checkIfRealPerson(JobSeeker jobSeeker);
}
