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
import hrms.exceptions.JobNotFoundException;

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
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.save(jobPosition), "Job position was saved.");
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Job positions are listed.");
    }

    @Override
    public DataResult<JobPosition> getById(int jobId) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job could not find by id:" + jobId)), "Job position found.");
    }

    private Result checkIfPositionNameExists(String jobTitle) {

        var result = this.jobPositionDao.existsByTitleIgnoreCase(jobTitle);

        if (result) {
            return new ErrorResult("Position already exists.");
        }
        return new SuccessResult("Not Found.");

    }

}
