package hrms.business.concretes;

import java.util.List;

import hrms.core.utils.results.*;
import hrms.core.verifications.abstracts.MernisVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.Business.BusinessRules;
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

        Result result = BusinessRules.Run(
                this.mernisVerificationService.checkIfRealPerson(jobSeeker),
                this.checkIfNationalIdAlreadyExists(jobSeeker),
                this.checkIfEmailAlreadyExists(jobSeeker));

        if (result != null) {
            return result;
        }
//        String encodedPassword = this.passwordEncoder.encode(jobSeeker.getPassword());
//        jobSeeker.setPassword(encodedPassword);
        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.save(jobSeeker), "User information is valid. User was saved.");
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Candidates are listed.");
    }

    private Result checkIfNationalIdAlreadyExists(JobSeeker jobSeeker) {

        var result = this.jobSeekerDao.existsJobSeekerByNationalId(jobSeeker.getNationalId());

        if (result) {
            return new ErrorResult("National-ID already exists.");
        }
        return new SuccessResult();
    }

    private Result checkIfEmailAlreadyExists(JobSeeker jobSeeker) {
        var result = this.jobSeekerDao.existsJobSeekerByEmail(jobSeeker.getEmail());

        if (result) {
            return new ErrorResult("Email already exists.");
        }
        return new SuccessResult();
    }

}
