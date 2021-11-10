package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.verifications.concretes.MernisVerificationManager;
import hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;
    private MernisVerificationManager mernisVerificationManager;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, MernisVerificationManager mernisVerificationManager) {
        this.jobSeekerDao = jobSeekerDao;
        this.mernisVerificationManager = mernisVerificationManager;
    }

    @Override
    public Result add(JobSeeker jobSeeker) throws Exception {
        
        Result result = BusinessRules.Run(this.mernisVerificationManager.checkIfRealPerson(jobSeeker));

        if (result != null) {
            return result;
        }
        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.save(jobSeeker), "The information is valid. User added. ");
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Candidates are listed.");
    }

}
