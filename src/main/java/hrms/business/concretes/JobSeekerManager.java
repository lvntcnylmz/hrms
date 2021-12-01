package hrms.business.concretes;

import java.util.List;

import hrms.core.verifications.abstracts.MernisVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final MernisVerificationService mernisVerificationService;
    //private PasswordEncoder passwordEncoder;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, MernisVerificationService mernisVerificationService) {
        this.jobSeekerDao = jobSeekerDao;
        this.mernisVerificationService = mernisVerificationService;
        //this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        
          Result result = BusinessRules.Run(this.mernisVerificationService.checkIfRealPerson(jobSeeker));

          if (result != null) {
              return result;
          }
//        String encodedPassword = this.passwordEncoder.encode(jobSeeker.getPassword());
//        jobSeeker.setPassword(encodedPassword);
        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.save(jobSeeker), "The information is valid. User was saved.");
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Candidates are listed.");
    }

}
