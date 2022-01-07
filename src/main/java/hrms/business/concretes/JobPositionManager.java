package hrms.business.concretes;

import hrms.business.abstracts.JobPositionsService;
import hrms.core.utils.businessRulesCheck.BusinessRules;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.*;
import hrms.dataAccess.abstracts.JobPositionDao;
import hrms.entities.concretes.JobPosition;
import hrms.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionsService {

    private final JobPositionDao jobPositionDao;

    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public Result add(JobPosition jobPosition) {

        var result = BusinessRules.Run(checkIfPositionNameExists(jobPosition.getTitle()));

        if (result != null) {
            return result;
        }
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.save(jobPosition), Message.SAVED);
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), Message.LISTED);
    }

    @Override
    public DataResult<JobPosition> getById(Integer id) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Message.NOT_FOUND)), Message.NOT_FOUND);
    }

    private Result checkIfPositionNameExists(String jobTitle) {

        var result = this.jobPositionDao.existsByTitleIgnoreCase(jobTitle);

        if (result) {
            return new ErrorResult(Message.EMAIL_EXISTS);
        }
        return new SuccessResult();

    }

}
