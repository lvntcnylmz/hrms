package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;

import java.util.List;

public interface JobAdvertisementService {
    
    Result add(JobAdvertisement jobAdvertisement);

    DataResult<List<JobAdvertisement>> getAll();

    DataResult<List<JobAdvertisementDto>> getByJobStatus();

    DataResult<List<JobAdvertisementDto>> getByDate();

    DataResult<List<JobAdvertisementDto>> getByCompanyName(String companyName);

    DataResult<JobAdvertisementDto> getJobById(Integer id);

}
