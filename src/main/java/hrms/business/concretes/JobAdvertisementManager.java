package hrms.business.concretes;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;
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
    public DataResult<List<JobAdvertisement>> getAll() {
        JobAdvertisementDto jobAdvertisementDto = this.modelMapper.map(new JobAdvertisement(), JobAdvertisementDto.class);
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Job advertisement listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByJobStatus() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByStatus(), "Job advertisement listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByDate() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByDate(), "Job advertisement listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByCompany(companyName), "Job advertisement listed.");
    }

    @Override
    public DataResult<JobAdvertisementDto> getJobById(Integer id) {
        return new SuccessDataResult<JobAdvertisementDto>(this.jobAdvertisementDao.findJobById(id).orElseThrow(), "Job advertisement listed.");
    }

}
