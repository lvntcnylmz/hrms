package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.dtos.request.JobAdvertisementRequestDto;
import hrms.entities.dtos.response.JobAdvertisementResponseDto;

import java.util.List;

public interface JobAdvertisementService {

    Result add(JobAdvertisementRequestDto jobAdvertisement);

    DataResult<List<JobAdvertisementResponseDto>> getAll();

    DataResult<List<JobAdvertisementResponseDto>> getByJobStatus();

    DataResult<List<JobAdvertisementResponseDto>> getByDate();

    DataResult<List<JobAdvertisementResponseDto>> getByCompanyName(String companyName);

    DataResult<JobAdvertisementResponseDto> getById(Integer id);

}
