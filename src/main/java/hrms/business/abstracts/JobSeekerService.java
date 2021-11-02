package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {

    Result add(JobSeeker jobSeeker);

    DataResult<List<JobSeeker>> getAll();

}
