package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobSeeker;

import java.util.List;

public interface JobSeekerService {

    Result add(JobSeeker jobSeeker);

    Result delete(Integer id);

    DataResult<List<JobSeeker>> getAll();

    DataResult<JobSeeker> getById(Integer id);

}
