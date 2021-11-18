package hrms.business.abstracts;

import java.util.List;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
    
    Result add(JobAdvertisement jobAdvertisement);

    DataResult<List<JobAdvertisementDto>> getAllAdvertisement();

    DataResult<List<JobAdvertisementDto>> getByJobStatus();

    DataResult<List<JobAdvertisementDto>> getByDate();

    DataResult<List<JobAdvertisementDto>> getByCompanyName(String companyName);

    DataResult<List<JobAdvertisementDto>> getJobById(int jobId);

}
