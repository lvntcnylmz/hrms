package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.dtos.request.ResumeRequestDto;
import hrms.entities.dtos.response.ResumeResponseDto;

import java.util.List;

public interface ResumeService {

    Result add(ResumeRequestDto resume);

    DataResult<List<ResumeResponseDto>> getAll();

}
