package hrms.business.concretes;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private final JobAdvertisementDao jobAdvertisementDao;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
        return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.save(jobAdvertisement), "Job advertisement was saved.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getAllAdvertisement() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findAllAdvertisement(), "Job advertisement listed.");
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
    public DataResult<List<JobAdvertisementDto>> getJobById(Integer id) {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findJobById(id), "Job advertisement listed.");
    }
    
}
