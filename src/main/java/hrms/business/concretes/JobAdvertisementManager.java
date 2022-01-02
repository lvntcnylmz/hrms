package hrms.business.concretes;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.response.JobAdvertisementDto;
import hrms.exceptions.JobNotFoundException;
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
    public Result add(JobAdvertisement jobAdvertisement) {
        return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.save(jobAdvertisement), "Job advertisement was saved.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getAll() {

        List<JobAdvertisementDto> jobAdvertisementDtos = this.jobAdvertisementDao.findAll()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisementDtos, "Job advertisements are listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByJobStatus() {

        List<JobAdvertisementDto> jobAdvertisements = this.jobAdvertisementDao.findByStatusTrue()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementDto.class)).toList();

        return new SuccessDataResult<>(jobAdvertisements, "Job advertisements are listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByDate() {

        List<JobAdvertisementDto> jobAdvertisements = this.jobAdvertisementDao.findByOrderByApplicationDeadlineAsc()
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisements, "Job advertisements are listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByCompanyName(String companyName) {

        List<JobAdvertisementDto> jobAdvertisements = this.jobAdvertisementDao.findByEmployer(companyName)
                .stream()
                .map(jobAdvertisement -> this.modelMapper.map(jobAdvertisement, JobAdvertisementDto.class))
                .toList();

        return new SuccessDataResult<>(jobAdvertisements, "Job advertisements are listed.");
    }

    @Override
    public DataResult<JobAdvertisementDto> getById(Integer id) {

        JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.findById(id).orElseThrow(() -> new JobNotFoundException("Not found by Id"));
        JobAdvertisementDto jobAdvertisementDto = this.modelMapper.map(jobAdvertisement, JobAdvertisementDto.class);

        return new SuccessDataResult<>(jobAdvertisementDto, "Job advertisement found by Id.");
    }

}
