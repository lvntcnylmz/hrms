package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobPosition;

public interface JobPositionsService {
    
    Result add(JobPosition jobPosition);

    DataResult<List<JobPosition>> getAll();

    DataResult<JobPosition> getById(int jobId);

}
