package hrms.business.concretes;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.request.JobAdvertisementRequestDto;
import hrms.entities.dtos.response.JobAdvertisementResponseDto;
import hrms.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private final JobAdvertisementDao jobAdvertisementDao;
    private final ModelMapper modelMapper;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, ModelMapper modelMapper) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(JobAdvertisementRequestDto jobAdvertisement) {

        JobAdvertisement jobAdvertisementRequest = this.modelMapper.map(jobAdvertisement, JobAdvertisement.class);
        jobAdvertisementRequest = this.jobAdvertisementDao.save(jobAdvertisementRequest);
        JobAdvertisementResponseDto jobAdvertisementResponseDto = this.modelMapper.map(jobAdvertisementRequest, JobAdvertisementResponseDto.class);

        return new SuccessDataResult<>(jobAdvertisementResponseDto, Message.SAVED);
    }

    @Override
    public DataResult<List<JobAdvertisementResponseDto>> getAll() {

        List<JobAdvertisementResponseDto> jobAdvertisementResponseDtos = this.jobAdvertisementDao.findAll()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementResponseDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisementResponseDtos, Message.LISTED);
    }

    @Override
    public DataResult<List<JobAdvertisementResponseDto>> getByJobStatus() {

        List<JobAdvertisementResponseDto> jobAdvertisements = this.jobAdvertisementDao.findByStatusTrue()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementResponseDto.class)).toList();

        return new SuccessDataResult<>(jobAdvertisements, Message.LISTED);
    }

    @Override
    public DataResult<List<JobAdvertisementResponseDto>> getByDate() {

        List<JobAdvertisementResponseDto> jobAdvertisements = this.jobAdvertisementDao.findByOrderByApplicationDeadlineAsc()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementResponseDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisements, Message.LISTED);
    }

    @Override
    public DataResult<List<JobAdvertisementResponseDto>> getByCompanyName(String companyName) {

        List<JobAdvertisementResponseDto> jobAdvertisements = this.jobAdvertisementDao.findByEmployerCompanyNameAllIgnoreCase(companyName)
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementResponseDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisements, Message.LISTED);
    }

    @Override
    public DataResult<JobAdvertisementResponseDto> getById(Integer id) {

        JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Message.NOT_FOUND));
        JobAdvertisementResponseDto jobAdvertisementResponseDto = this.modelMapper.map(jobAdvertisement, JobAdvertisementResponseDto.class);

        return new SuccessDataResult<>(jobAdvertisementResponseDto, Message.FOUND);
    }

}
