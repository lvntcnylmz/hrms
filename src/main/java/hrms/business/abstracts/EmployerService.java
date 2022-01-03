package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.dtos.request.EmployerRegisterDto;
import hrms.entities.dtos.response.EmployerResponseDto;

import java.util.List;

public interface EmployerService {

    Result add(EmployerRegisterDto employer);

    Result delete(Integer id);

    DataResult<List<EmployerResponseDto>> getAll();

    DataResult<EmployerResponseDto> getById(Integer id);

}
