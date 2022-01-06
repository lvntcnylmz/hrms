package hrms.business.concretes;

import hrms.business.abstracts.ResumeService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.ResumeDao;
import hrms.entities.concretes.Resume;
import hrms.entities.dtos.request.ResumeRequestDto;
import hrms.entities.dtos.response.ResumeResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;
    private final ModelMapper modelMapper;

    public ResumeManager(ResumeDao resumeDao, ModelMapper modelMapper) {
        this.resumeDao = resumeDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(ResumeRequestDto resume) {
        Resume resumeRequest = this.modelMapper.map(resume, Resume.class);
        resumeRequest = this.resumeDao.save(resumeRequest);
        ResumeResponseDto resumeResponseDto = this.modelMapper.map(resumeRequest, ResumeResponseDto.class);
        return new SuccessDataResult<>(resumeResponseDto, "Resume was saved.");
    }

    @Override
    public DataResult<List<ResumeResponseDto>> getAll() {

        List<ResumeResponseDto> resumes = this.resumeDao.findAll()
                .stream()
                .map(resume -> this.modelMapper.map(resume, ResumeResponseDto.class))
                .toList();

        return new SuccessDataResult<>(resumes, "Resumes are listed.");
    }

}
