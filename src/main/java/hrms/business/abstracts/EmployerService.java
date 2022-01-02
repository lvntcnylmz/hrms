package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Employer;
import hrms.entities.dtos.response.EmployerResponseDto;

import java.util.List;

public interface EmployerService {

    Result add(Employer employer);

    Result delete(Integer id);

    DataResult<List<EmployerResponseDto>> getAll();

    DataResult<EmployerResponseDto> getById(Integer id);

}
