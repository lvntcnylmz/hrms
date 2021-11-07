package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
    
    Result add(JobAdvertisement jobAdvertisement);

    DataResult<List<JobAdvertisement>> getAll();

}
