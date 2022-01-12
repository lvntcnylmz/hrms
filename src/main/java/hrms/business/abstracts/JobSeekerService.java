package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Contact;
import hrms.entities.dtos.request.JobSeekerRegisterDto;
import hrms.entities.dtos.response.JobSeekerResponseDto;

import java.util.List;

public interface JobSeekerService {

    Result add(JobSeekerRegisterDto jobSeeker);

    Result delete(Integer id);

    DataResult<List<JobSeekerResponseDto>> getAll();

    DataResult<JobSeekerResponseDto> getById(Integer id);

}
