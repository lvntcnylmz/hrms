package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobPositionsService;
import hrms.core.utils.Business.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.utils.results.SuccessResult;
import hrms.dataAccess.abstracts.JobPositionDao;
import hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionsService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public Result add(JobPosition jobPosition) {

        var result = BusinessRules.Run(checkIfPositionNameExists(jobPosition.getTitle()));

        if (result != null) {
            return result;
        }
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.save(jobPosition));
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Job positions listed.");
    }

    private Result checkIfPositionNameExists(String jobTitle){
        
        var result = this.jobPositionDao.existsByTitleIgnoreCase(jobTitle);


        if (result) {
            return new ErrorResult("Position already exists.");
        }
        return new SuccessResult("Not Found.");

    }

    @Override
    public DataResult<List<JobPosition>> getById(int jobId) {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findById(jobId), "Found");
    }

}
