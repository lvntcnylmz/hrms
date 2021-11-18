package hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;
import hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao jobAdvertisementDao;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
        return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.save(jobAdvertisement), "Added.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getAllAdvertisement() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findAllAdvertisement(), "Listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByJobStatus() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByStatus(), "Listed.");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByDate() {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByDate(), "Listed");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getByCompanyName(String companyName) {
       return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findByCompany(companyName), "Listed");
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getJobById(int jobId) {
        return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.findJobById(jobId), "Listed");
    }
    
}
